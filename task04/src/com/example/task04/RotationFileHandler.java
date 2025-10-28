package com.example.task04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final String baseFilename;
    private final ChronoUnit rotationUnit;
    private BufferedWriter writer;
    private LocalDateTime lastRotation;

    public RotationFileHandler(String baseFilename, ChronoUnit unit) throws IOException {
        if (baseFilename == null || baseFilename.trim().isEmpty()) {
            throw new IllegalArgumentException("Base filename cannot be null or empty");
        }
        this.baseFilename = baseFilename;
        this.rotationUnit = unit;
        this.lastRotation = LocalDateTime.now();
        rotate();
    }

    private void rotate() throws IOException {
        if (writer != null) {
            writer.close();
        }
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")
                .format(lastRotation);
        String filename = baseFilename + "-" + timestamp + ".log";
        this.writer = new BufferedWriter(new FileWriter(filename));
    }

    @Override
    public void publish(String formattedMessage) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        if (rotationUnit.between(lastRotation, now) >= 1) {
            lastRotation = now;
            rotate();
        }
        writer.write(formattedMessage);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
