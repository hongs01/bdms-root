package com.bdms.ftp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
 
/**
 * 接受服务器信息
 */
class ReadMes extends Thread {
    private Socket socket;
 
    public ReadMes(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String message = null;
            while (true) {
                message = reader.readLine();
                // 当读服务器信息线程接收到bye，该线程退出
                if (message.equals("bye")) {
                    break;
                }
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
}
 

 
public class ReadC {
    private String ipAdress="192.168.7.169";
 
    
 
    public void startWork() throws UnknownHostException, IOException {
        Socket socket = new Socket(ipAdress, 2345);
        new ReadMes(socket).start();
         
 
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
        ReadC chatClient = new ReadC();
        chatClient.startWork();
    }
 
}