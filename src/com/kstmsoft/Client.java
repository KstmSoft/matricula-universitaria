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
    private Logs logs;

    public void connect(String address, int port) {
        try {
            socket = new Socket(address, port);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            System.out.println("Connected Successfully!");
            logs = new Logs();
            logs.initLogging();
        } catch (UnknownHostException unknownHostException) {
            System.out.println(unknownHostException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public boolean studentExist(String id){
        Request request = new Request("student_exist", id);
        logs.writeLog("Estudiante " + id + " ha iniciado sesión.");
        return (boolean)sendRequest(request);
    }

    public ArrayList<Course> getCourses(String id) {
        Request request = new Request("get_courses", id);
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

    public void addCourse(String id, String courseId){
        Request request = new Request("add_course", id, courseId);
        logs.writeLog("Estudiante " + id + " ha matriculado la asignatura " + courseId);
        sendRequest(request);
    }

    public void cancelCourse(String id, String courseId){
        Request request = new Request("cancel_course", id, courseId);
        logs.writeLog("Estudiante " + id + " ha cancelado la asignatura " + courseId);
        sendRequest(request);
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
