package com.opticalstore.commons.extras;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.opticalstore.commons.Naming.LOCAL_PATH;

public class DirectoryCreator {

    public static void createDirectory() {
        if(Files.notExists(Paths.get(LOCAL_PATH))) {
            try {
                Files.createDirectories(Paths.get(LOCAL_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
