package com.kstmsoft;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    protected Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void connect(String address, int port) {
        try {
            socket = new Socket(address, port);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            System.out.println("Connected Successfully!");
        } catch (UnknownHostException unknownHostException) {
            System.out.println(unknownHostException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public ArrayList<Course> login(String id) {
        Request request = new Request("login", id);
        return (ArrayList<Course>) sendRequest(request);
    }

    public int getCourseCredits(String id){
        Request request = new Request("course_credits", id);
        return (int)sendRequest(request);
    }

    public int getCourseQuota(String id){
        Request request = new Request("course_quota", id);
        return (int)sendRequest(request);
    }

    private Object sendRequest(Request request){
        Object response = null;
        try {
            outputStream.writeObject(request);
            outputStream.flush();
            response = inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
