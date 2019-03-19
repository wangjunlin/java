package com.demo.RPCServer;

public class PrintlnServiceImpl implements PrintlnService {
    @Override
    public String printlnOnConsole(String str) {
        System.out.println("RPCServer : " + str);
        return str;
    }
}
