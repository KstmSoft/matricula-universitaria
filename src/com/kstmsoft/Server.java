package com.kstmsoft;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class Server {

    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private Database database;

    private Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            database = new Database();

            System.out.println("Server Running!\n" +
                               "Waiting for client connections ...");
            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println("New connection from: " + socket.getRemoteSocketAddress());
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.flush();
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    processConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    outputStream.close();
                    inputStream.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processConnection() throws IOException{
        Request request;
        while (true){
            try{
                request = (Request) inputStream.readObject();
                switch (request.getQuery()){
                    case "login":
                        sendToClient(login(request.getArgs()));
                        break;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Course> login(String id){
        ArrayList<Course> courses = new ArrayList<>();
        String sql;
        sql = "SELECT enrolled_students.course_id AS id," +
                " courses.name AS course_name," +
                " courses.credits AS credits" +
                " FROM enrolled_students JOIN courses" +
                " ON enrolled_students.course_id = courses.id" +
                " WHERE enrolled_students.student_id = " + id;

        try{
            Connection connection = database.connect();
            Statement statement = connection.createStatement();
            ResultSet table = statement.executeQuery(sql);
            while(table.next()){
                courses.add(new Course(table.getString(1), table.getString(2), table.getInt(3)));
            }
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return courses;
    }

    private void sendToClient(Object response){
        try {
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
    }
}

class Database{
    String url, user, password;
    Connection connection;

    public Database(){
        url = "jdbc:mysql://localhost:3306/sira";
        user = "root";
        password = "";
    }

    public Connection connect(){
        try{
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException throwables) {
            System.out.println("Error connecting to DB: " + throwables.getMessage());
            return null;
        }
    }
}