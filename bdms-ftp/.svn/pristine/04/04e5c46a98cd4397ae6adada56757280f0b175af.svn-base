package com.bdms.ftp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
 
/**
 * 
 */
class Chat extends Thread {
    private Socket socket;
    private List<Socket> socketList;
    private int count;
 
    public Chat(int count, Socket socket, List<Socket> socketList) {
        this.count = count;
        this.socket = socket;
        this.socketList = socketList;
    }
 
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
 
            String message = null;
//            while ((message=reader.readLine())!=null && (message=reader.readLine())!="") {
//            	
//            	System.out.println(message);
//                //message = reader.readLine();
//                // 接收到客户端的bye信息，客户端即将退出，并将bye写入到该客户端
//                if (message.equals("bye")) {
//                    writer = new PrintWriter(socket.getOutputStream());
//                    writer.println("bye");
//                    writer.flush();
//                    continue;
//                }
// 
//                // 向所有的客户端发送接收到信息，实现群聊
//            }
            
            System.out.println("data received");
            System.out.println(socketList.size());
            for (int i = 0; i < socketList.size(); i++) {
            	System.out.print(socketList.get(i).isClosed());
                writer = new PrintWriter(socketList.get(i)
                        .getOutputStream());
                writer.println("I'm done");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 
public class ChatServer {
 
    /**
     * Description
     *
     * @param args
     */
    public void startWork() throws IOException {

        @SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(12345);
        List<Socket> socketList = new ArrayList<Socket>();
        Socket socket = null;
        int count = 0;
        while (true) {
            socket = serverSocket.accept();
            count++;
            System.out.println(count + " clinet connected to the server!");
            // 将每一个连接到该服务器的客户端，加到List中
            socketList.add(socket);
            // 每一个连接到服务器的客户端，服务器开启一个新的线程来处理
            new Chat(count, socket, socketList).start();
        }
    }
 
    /**
     * Description
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        chatServer.startWork();
    }
 
}