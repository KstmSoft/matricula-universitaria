package com.kstmsoft;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs {
    String filename = "logs.txt";
    public void initLogging(){
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("Log file created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void writeLog(String log){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String content = "["+dtf.format(now)+"]: "+log+"\n";
        try {
            Files.write(
                    Paths.get(filename),
                    content.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
