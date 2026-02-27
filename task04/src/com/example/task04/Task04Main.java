package com.example.task04;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Task04Main {
    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.log("Сообщение 1");

        FileHandler fileHandler = new FileHandler();
        fileHandler.log("Сообщение 2");

        RotationFileHandler rotationFileHandler = new RotationFileHandler(ChronoUnit.DAYS);
        rotationFileHandler.log("Сообщение 3");

        ArrayList<MessageHandler> handlers = new ArrayList<>();
        handlers.add(consoleHandler);
        handlers.add(fileHandler);

        MemoryHandler memoryHandler = new MemoryHandler(2, handlers);
        memoryHandler.log("Сообщение 4");
        memoryHandler.log("...");
    }
}