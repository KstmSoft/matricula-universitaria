package com.kstmsoft;
import java.io.*;
import java.net.*;

public class Server{
    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server(int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server Running!");
            System.out.println("Waiting for client connections ...");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        while(true){
            try{
                socket = serverSocket.accept();
                System.out.println("A client has been connected: " + socket.getRemoteSocketAddress());
                new ClientThread(socket).start();
            }catch (IOException exception){
                System.out.println("I/O Error: " + exception);
            }
        }
    }
    public static void main(String[] args) {
        Server server = new Server(8080);
    }
}

class ClientThread extends Thread {
    protected Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String line;
        while (true) {
            try {
                line = (String) inputStream.readObject();
                System.out.println("test");
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    outputStream.flush();
                    System.out.println("Response to client: " + socket.getRemoteSocketAddress());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}