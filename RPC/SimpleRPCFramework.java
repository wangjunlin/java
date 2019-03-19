package com.demo.RPCServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class RPCFramework {
    public static void export(final Object service, int port) throws IOException {
        if (service == null) {
            throw new IllegalArgumentException("service instance is null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }

        ServerSocket serverSocket = new ServerSocket(port);
        for (; ; ) {
            final Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                         ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                        String methodName = inputStream.readUTF();
                        System.out.println("method name: " + methodName);
                        Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                        Object[] arguments = (Object[]) inputStream.readObject();
                        System.out.println("parameter types: " + Arrays.toString(parameterTypes));

                        /*反射调用Object类型*/
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, arguments);
                        outputStream.writeObject(result);
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 引用服务
     *
     * @param interfaceClass 接口类型
     * @param host           服务器主机
     * @param port           服务器端口
     * @param <T>            接口泛型
     * @return 远程服务，代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("interfaceClass is null");
        }
        /*JDK动态代理的约束，只能实现对接口的代理*/
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException(interfaceClass.getName() + " must be interface class!");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("host is null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("invalid port");
        }

        T proxy = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                            /*将对象写入到对象输出流，并将对象发送到Socket流中*/
                            System.out.println("--------------------method--------------------");
                            outputStream.writeUTF(method.getName());
                            System.out.println("method name: " + method.getName());
                            outputStream.writeObject(method.getParameterTypes());
                            System.out.println("method parameterTypes: " + Arrays.toString(method.getParameterTypes()));
                            outputStream.writeObject(args);
                            System.out.println("method arguments: " + Arrays.toString(args));

                            /*get response from server*/
                            Object result = inputStream.readObject();
                            if (result instanceof Throwable){
                                throw (Throwable) result;
                            }
                            System.out.println("server response: "+result);
                            return result;
                        }
                    }
                });
        return proxy;
    }
}
