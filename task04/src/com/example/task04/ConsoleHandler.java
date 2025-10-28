package com.example.task04;

public class ConsoleHandler implements MessageHandler{

    @Override
    public void publish(String formattedMessage) {
        System.out.println(formattedMessage);
    }

    @Override
    public void close() {

    }
}
