package com.example.task04;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler{

    private final BufferedWriter writer ;

    public FileHandler(String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым");
        }
        this.writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    @Override
    public void publish(String formattedMessage) throws IOException {
        if (formattedMessage == null) {
            throw new IllegalArgumentException("Сообщение не может быть null");
        }
        writer.write(formattedMessage);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws IOException{
        writer.close();
    }

}
