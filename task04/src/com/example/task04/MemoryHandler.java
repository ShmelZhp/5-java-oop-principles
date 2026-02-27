package com.example.task04;

import java.util.ArrayList;

public class MemoryHandler implements MessageHandler {
    private final int maxOfMessages;
    private final ArrayList<MessageHandler> handlers;
    private final ArrayList<String> messages = new ArrayList<>();

    public MemoryHandler(int maxOfMessages, ArrayList<MessageHandler> handlers){
        this.maxOfMessages = maxOfMessages;
        this.handlers = handlers;
    }

    //Добавление сообщения в массив и вывод накопленных сообщений при достаточном их количестве
    @Override
    public void log(String message) {
        messages.add(message);

        if(messages.size() >= maxOfMessages){
            for(MessageHandler handler : handlers){
                for(String m : messages){
                    handler.log(m);
                }
                messages.clear();
            }
        }
    }
}