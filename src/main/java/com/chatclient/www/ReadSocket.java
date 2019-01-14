package com.chatclient.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReadSocket implements Runnable{
    private Socket socket;
    BufferedReader ois;

    public ReadSocket(Socket socket) throws IOException {
        this.socket = socket;
        ois = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        String receiveData = null;
        try {
            while( (receiveData = ois.readLine()) != null) {
                System.out.println(receiveData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
