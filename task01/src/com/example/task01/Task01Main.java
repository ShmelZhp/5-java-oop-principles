package com.example.task01;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

class Logger {

    public enum Level{
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private final String name;
    private static final Map<String, Logger> loggers = new HashMap<>();
    private Level level = Level.DEBUG;

    private Logger(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Logger getLogger(String nickName){
        if (!loggers.containsKey(nickName)){
            loggers.put(nickName, new Logger(nickName));
        }
        return loggers.get(nickName);
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public Level getLevel(){
        return level;
    }

    private String formatTimestamp(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date()) + " " + timeFormat.format(new Date());
    }

    public void log(Level level, String message){
        if (level.ordinal() >= this.level.ordinal()){
            System.out.println(String.format("[%s] %s %s - %s", level, formatTimestamp(), name, message));
        }
    }

    public void log (Level level, String format, Object... args){
        if (level.ordinal() >= this.level.ordinal()){
            String message = String.format(format, args);
            System.out.println(String.format("[%s] %s %s - %s", level, formatTimestamp(), name, message));
        }
    }

    public void debug(String message){
        log(Level.DEBUG, message);
    }

    public void debug(String format, Object... args){
        log(Level.DEBUG, format, args);
    }

    public void info(String message){
        log(Level.INFO, message);
    }

    public void info(String format, Object... args){
        log(Level.INFO, format, args);
    }

    public void warning(String message){
        log(Level.WARNING, message);
    }

    public void warning(String format, Object... args){
        log(Level.WARNING, format, args);
    }

    public void error(String message){
        log(Level.ERROR, message);
    }

    public void error(String format, Object... args){
        log(Level.ERROR, format, args);
    }



}

public class Task01Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        logger.setLevel(Logger.Level.WARNING);
        logger.debug("Это сообщение не появится");
        logger.warning("Предупреждение: что-то пошло не так");
        logger.error("Ошибка: %s", "критическая ситуация");

    }
}
