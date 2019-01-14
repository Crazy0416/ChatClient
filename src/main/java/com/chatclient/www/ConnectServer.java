package com.chatclient.www;

import java.io.IOException;
import java.net.*;

public class ConnectServer {
    private Socket socket;
    private InetSocketAddress inetSocketAddress;

    public ConnectServer(String ip, int port) throws UnknownHostException {
        inetSocketAddress = new InetSocketAddress(ip, port);
    }

    public void connect() throws IOException {

        socket = new Socket();
        if(inetSocketAddress == null) {
            throw new IOException("서버의 ip와 port를 지정하지 않았습니다.");
        }

        socket.connect(inetSocketAddress);

        System.out.println(inetSocketAddress + "와 연결되었습니다.");


        Thread thread1 = new Thread(new ReadSocket(this.socket));
        Thread thread2 = new Thread(new WriteSocket(this.socket));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
        this.inetSocketAddress = inetSocketAddress;
    }
}
