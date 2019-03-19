package com.demo.RPCClient;

import com.demo.RPCServer.RPCFramework;

public class RPCClientStart {
    public static void main(String[] args) {
        PrintlnService service = RPCFramework.refer(PrintlnService.class, "10.151.97.20", 12345);
        String printText = service.printlnOnConsole("test text");
        System.out.println("remote call result : " + printText);
    }
}
