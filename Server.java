package com.kstmsoft;

import java.io.*;
import java.net.*;

public class Server{
    private DataInputStream inputStream = null;
    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server(int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server Running!");
            System.out.println("Waiting for client connections ...");
            socket = serverSocket.accept();
            System.out.println("A client has been connected: " + socket.getRemoteSocketAddress());
            inputStream = new DataInputStream(socket.getInputStream());
            String response = "";
            while(!response.equals("close")){
                try{
                    response = inputStream.readUTF();
                    System.out.println(response);
                }catch (IOException exception){
                    System.out.println(exception);
                }
            }
            socket.close();
            inputStream.close();
            System.out.println("Connection closed");
        }catch (IOException ioException){
            System.out.println(ioException);
        }
    }
    public static void main(String[] args) {
        Server server = new Server(8080);
    }
}