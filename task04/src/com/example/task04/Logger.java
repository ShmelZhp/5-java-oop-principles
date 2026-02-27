package com.example.task04;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
    private final String name;
    private static ArrayList<Logger> loggersList;
    private LogLevel logLevel;

    public Logger(String name){
        this.name = name;
    }

    public enum LogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public Logger(){
        this.name = "Logger";
    }

    public String getName(){
        return name;
    }

    public static Logger getLogger(String name){
        for(Logger i : loggersList){
            if(i.name.equals(name))
                return i;
        }
        Logger logger = new Logger(name);
        loggersList.add(logger);
        return logger;
    }

    public void error(String message){
        log(LogLevel.ERROR, message);
    }

    public void error(String format, Object... args){
        log(LogLevel.ERROR, format, args);
    }

    public void warning(String message){
        log(LogLevel.WARNING, message);
    }

    public void warning(String format, Object... args){
        log(LogLevel.WARNING, format, args);
    }

    public void info(String message){
        log(LogLevel.INFO, message);
    }

    public void info(String format, Object... args){
        log(LogLevel.INFO, format, args);
    }

    public void debug(String message){
        log(LogLevel.DEBUG, message);
    }

    public void debug(String format, Object... args){
        log(LogLevel.DEBUG, format, args);
    }

    private String formatMessage(LogLevel logLevel, String Message){
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        return MessageFormat.format("[{0}] {1} {2} - {3}", logLevel.name(), date, this.name, Message);
    }

    public void log(LogLevel logLevel, String message){
        if(logLevel.compareTo(this.logLevel) >= 0)
            System.out.println(formatMessage(logLevel, message));
    }

    public void log(LogLevel logLevel, String format, Object... args){
        if(logLevel.compareTo(this.logLevel) >= 0)
            System.out.println(formatMessage(logLevel, String.format(format, args)));
    }

    public LogLevel getLevel(){
        return logLevel;
    }

    public void setLevel(LogLevel logLevel){
        this.logLevel = logLevel;
    }
}