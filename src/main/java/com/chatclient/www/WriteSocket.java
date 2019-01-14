package com.chatclient.www;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteSocket implements Runnable{

    private Socket socket;
    PrintWriter oos;
    Scanner scanner;

    public WriteSocket(Socket socket) throws IOException {
        this.socket = socket;
        oos = new PrintWriter(socket.getOutputStream());
        scanner = new Scanner(System.in);
    }

    public void chatting() {
        String sendData;
        JsonObject json;

        System.out.println("방에 입장하였습니다.");

        while(true) {
            sendData = scanner.nextLine();
            json = new JsonObject();
            json.addProperty("message", sendData);

            oos.println(json);
            oos.flush();

            if(sendData == "@quit") {
                json = new JsonObject();

                json.addProperty("command","@quit");

            }
        }
    }

    public String showChoice() {
        System.out.println("1. 채팅방 생성");
        System.out.println("2. 채팅방 보기");
        System.out.println("3. 채팅방 참여");

        return scanner.nextLine();
    }

    public void createRoom() {
        JsonObject json=new JsonObject();
        String dump;
        json.addProperty("command","@create");

        System.out.println("방 제목을 정하세요.");
        dump = scanner.nextLine();
        json.addProperty("title", dump);

        System.out.println("방의 정보를 적어주세요");
        dump = scanner.nextLine();
        json.addProperty("roomInfo",dump);

        System.out.println("방의 매니저 id를 적어주세요");
        dump = scanner.nextLine();
        json.addProperty("managerId",dump);

        System.out.println("방의 매니저 이름을 적어주세요");
        dump = scanner.nextLine();
        json.addProperty("managerName",dump);

        oos.println(json);
        oos.flush();

        chatting();
    }

    public void showRoom() {
        JsonObject json = new JsonObject();
        json.addProperty("command","@list");

        oos.println(json);
        oos.flush();
    }

    public void joinRoom() {
        JsonObject json = new JsonObject();
        String dump;

        json.addProperty("command","@join");

        System.out.println("방의 roomId를 적어주세요");
        dump = scanner.nextLine();
        json.addProperty("roomId",dump);

        System.out.println("자신의 uid를 적어주세요");
        dump = scanner.nextLine();
        json.addProperty("uid",dump);

        System.out.println("자신의 name을 적어주세요");
        dump = scanner.nextLine();
        json.addProperty("name",dump);

        oos.println(json);
        oos.flush();

        chatting();
    }

    @Override
    public void run() {
        String sendData = null;
        Scanner scanner = new Scanner(System.in);


        while (true) {
            String choice;
            boolean check = false;
            try {
                choice = showChoice();

                switch (choice) {
                    case "1":
                        createRoom();
                        break;
                    case "2":
                        showRoom();
                        break;
                    case "3":
                        joinRoom();
                        break;
                    default:
                        check = true;
                        break;
                }
                if(check)
                    continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
