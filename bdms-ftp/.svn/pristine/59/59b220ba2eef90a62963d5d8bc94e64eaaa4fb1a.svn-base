package com.bdms.ftp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
 

/**
 *
 * 发送信息
 */
class SendMes extends Thread {
    private Socket socket;
 
    public SendMes(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {
        BufferedReader input = null;
        PrintWriter writer = null;
        try {
            input = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(socket.getOutputStream());
            String message = null;
            while (true) {
                message = input.readLine();
                // 当输入bye客户端退出
                if (message.equals("bye")) {
                    break;
                }
                // 向服务器端发送信息
                writer.println(message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
 
public class SendC {
    private String ipAdress="192.168.7.169";
 
   
    public void startWork() throws UnknownHostException, IOException {
        Socket socket = new Socket(ipAdress, 2345);
        new SendMes(socket).start();
         
 
    }
 
    /**
     * Description
     *
     * @param args
     * @throws UnknownHostException
     * @throws IOException
     */
    public static void main(String[] args) throws UnknownHostException,
            IOException {
        SendC chatClient = new SendC();
        chatClient.startWork();
    }
 
}