package com.example.task04;

import java.io.IOException;

public interface MessageHandler {

    void publish(String formattedMessage) throws IOException;
    void close() throws IOException;

}
