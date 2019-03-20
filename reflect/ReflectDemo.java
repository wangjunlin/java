import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, NoSuchFieldException {
//        getClassInstance();
//        getParentAndInterface();
//        getConstructors();
//        getClassArguments();
//        getClassMethods();
//        getMethodByReflect();
//        getParameterByReflect();
//        getArrayListMethodAddObject();
//        getArrayInformation();
    }

    public static void getClassInstance() throws ClassNotFoundException {
        Class<?> class1;
        Class<?> class2;
        Class<?> class3;

        class1 = Class.forName("com.demo.reflect.Goods");
        class2 = new Goods().getClass();
        class3 = Goods.class;
        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class3);
    }

    public static void getParentAndInterface() {
        Class<?> clazz = MyInterfaceImpl.class;
        /*获取父类*/
        Class<?> parentClass = clazz.getSuperclass();
        System.out.println(parentClass.getName());
        /*获取所有接口*/
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println(Arrays.toString(interfaces));
    }

    public static void getConstructors() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz;
        clazz = Goods.class;
        /*实例化默认构造方法，调用set*/
        Goods goods = (Goods) clazz.newInstance();
        goods.setId(1);
        goods.setGoodName("book");
        System.out.println("goods : " + goods);

        /*取得全部构造函数，使用构造函数赋值*/
        Constructor<?>[] constructors = clazz.getConstructors();
        Goods tempGoods;
        /*查看每个构造方法需要的参数*/
        for (Constructor<?> cons : constructors) {
            Class<?>[] classes = cons.getParameterTypes();
            StringBuilder tempArgs = new StringBuilder();
            for (Class<?> tempClass : classes) {
                tempArgs.append(tempClass.getName()).append("|");
            }
            System.out.println(String.format("constructor: %s , args: %s", cons, tempArgs.toString()));
        }
        /*getConstructors()获取构造器的栈，先取出的构造器放入Class<?>[]栈底*/
        tempGoods = (Goods) constructors[0].newInstance(20, "aaaa");
        System.out.println("tempGoods : " + tempGoods);
        tempGoods = (Goods) constructors[1].newInstance(150);
        System.out.println("tempGoods : " + tempGoods);
        tempGoods = (Goods) constructors[2].newInstance();
        System.out.println("tempGoods : " + tempGoods);
    }

    public static void getClassArguments() {
        Class<?> clazz = MyInterfaceImpl.class;
        /*本类属性*/
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            /*权限修饰符*/
            int mo = field.getModifiers();
            String moString = Modifier.toString(mo);
            System.out.println(moString);
            /*属性类型*/
            Class<?> type = field.getType();
            System.out.println(type.getName());
        }

        /*父类属性或实现的接口*/
        Field[] parentFields = clazz.getFields();
        for (Field field : parentFields) {
            int parentMo = field.getModifiers();
            String parentMoString = Modifier.toString(parentMo);
            System.out.println(parentMoString);
            Class<?> parentType = field.getType();
            System.out.println(parentType.getName());
        }
    }

    public static void getClassMethods() {
        /*无法拿到private的方法*/
        Class<?> clazz = MyInterfaceImpl.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            Class<?>[] parameters = method.getParameterTypes();
            String moString = Modifier.toString(method.getModifiers());
            System.out.println(String.format("方法名字： %s , 权限 ：%s ,返回类型： %s , 入参： %s",
                    method.getName(), moString, returnType, Arrays.toString(parameters)));
        }
    }

    public static void getMethodByReflect() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = MyInterfaceImpl.class;
        Method method = clazz.getDeclaredMethod("setTest", String.class);
        method.setAccessible(true);
        method.invoke(clazz.newInstance(),"111");
    }

    public static void getParameterByReflect() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<?> clazz = MyInterfaceImpl.class;
        Object object = clazz.newInstance();
        Field field = clazz.getDeclaredField("test");
        field.setAccessible(true);
        field.set(object,"java reflect set value");
        System.out.println(field.get(object));
    }

    /**
     * 在泛型为Integer的ArrayList中存放String类型对象
     * 为什么只能传入Object.class
     */
    public static void getArrayListMethodAddObject() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        /*通过DEBUG可以发现add()方法的入参是java.lang.Object*/
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list,"aaa");
        System.out.println(list);
    }

    public static void getArrayInformation(){
        int[] temp = {1,2,3,4,5};
        Class<?> clazz = temp.getClass().getComponentType();
        System.out.println(clazz.getName());
        System.out.println(Array.get(temp,0));
        Array.set(temp,0,11);
        System.out.println(Arrays.toString(temp));
    }
}
