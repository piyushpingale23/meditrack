package com.airtribe.meditrack.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvFileManager {

    private static CsvFileManager instance;

    private CsvFileManager() {
    }

    public static CsvFileManager getInstance() {

        if (instance == null) {
            instance = new CsvFileManager();
        }

        return instance;
    }

    public void write(Path path, List<String> lines) {

        try {
            Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to write CSV file: " + path,
                    e
            );
        }
    }

    public List<String> read(Path path) {

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read CSV file: " + path,
                    e
            );
        }
    }
}