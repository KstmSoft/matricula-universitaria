package com.kstmsoft;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    protected Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Client (String address, int port){
        try{
            socket = new Socket(address, port);
            System.out.println("Connected Successfully!");

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
        }catch (UnknownHostException unknownHostException){
            System.out.println(unknownHostException);
        }catch (IOException ioException){
            System.out.println(ioException);
        }
    }

    public void login(){
        try {
            outputStream.writeObject("login");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
