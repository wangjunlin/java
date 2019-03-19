package com.demo.RPCServer;

import java.io.IOException;

public class RPCServerStart {
    public static void main(String[] args) throws IOException {
        PrintlnService printlnService = new PrintlnServiceImpl();
        RPCFramework.export(printlnService,12345);
    }
}
