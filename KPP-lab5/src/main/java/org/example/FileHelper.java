package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

    public static String readFromFile(String filename) throws IOException {
        Path filePath = Paths.get(filename);
        return new String(Files.readAllBytes(filePath));
    }
}
