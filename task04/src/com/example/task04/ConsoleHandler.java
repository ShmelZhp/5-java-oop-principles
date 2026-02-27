package com.example.task04;

public class ConsoleHandler implements MessageHandler {
    //выводит сообщение
    @Override
    public void log(String message){
        System.out.println(message);
    }
}