package com.example.task04;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {



    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private final String name;
    private static final Map<String, Logger> loggers = new HashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();
    private Level level = Level.DEBUG;
    private MessageHandler handler; // Добавлен обработчик

    private Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Logger getLogger(String nickName) {
        lock.lock();
        try {
            Logger logger = loggers.get(nickName);
            if (logger == null) {
                logger = new Logger(nickName);
                loggers.put(nickName, logger);
            }
            return logger;
        } finally {
            lock.unlock();
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    // Установщик обработчика
    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }

    private String formatTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    private void logInternal(Level level, String message) throws IOException {
        if (level.ordinal() >= this.level.ordinal()) {
            String formatted = String.format("[%s] %s %s - %s",
                    level, formatTimestamp(), name, message);

            if (handler != null) {
                handler.publish(formatted);
            } else {
                System.out.println(formatted); // Резерв
            }
        }
    }

    public void log(Level level, String message) {
        try {
            logInternal(level, message);
        } catch (IOException e) {
            System.err.println("Ошибка записи лога: " + e.getMessage());
        }
    }

    public void log(Level level, String format, Object... args) {
        try {
            String message = String.format(format, args);
            logInternal(level, message);
        } catch (IOException e) {
            System.err.println("Ошибка записи лога: " + e.getMessage());
        }
    }

    public MessageHandler getHandler() {
        return this.handler;
    }


    // Методы-обёртки (без изменений)
    public void debug(String message) { log(Level.DEBUG, message); }
    public void debug(String format, Object... args) { log(Level.DEBUG, format, args); }
    public void info(String message) { log(Level.INFO, message); }
    public void info(String format, Object... args) { log(Level.INFO, format, args); }
    public void warning(String message) { log(Level.WARNING, message); }
    public void warning(String format, Object... args) { log(Level.WARNING, format, args); }
    public void error(String message) { log(Level.ERROR, message); }
    public void error(String format, Object... args) { log(Level.ERROR, format, args); }
}
