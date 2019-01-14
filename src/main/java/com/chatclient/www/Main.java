package com.chatclient.www;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        try {
            ConnectServer connectServer = new ConnectServer(java.net.InetAddress.getLocalHost().getHostName(), 8888);
            connectServer.connect();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
