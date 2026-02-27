package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final ChronoUnit rotationUnit;

    public RotationFileHandler(ChronoUnit rotationUnit){
        this.rotationUnit = rotationUnit;
    }

    //Запсивает сообщение в файл определенного временного интервала
    @Override
    public void log(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String fileName = LocalDateTime.now().truncatedTo(rotationUnit).format(formatter) + ".log";
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(message);
            writer.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}