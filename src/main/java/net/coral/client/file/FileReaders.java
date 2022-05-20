package net.coral.client.file;

import net.coral.client.Coral;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReaders {
    public static String read(File path) {
        if(path.exists()) {
            try {
                return String.join("", new ArrayList<>(Files.readAllLines(Paths.get(path.toString()))));
            } catch(Exception e) {
                Coral.LOGGER.error(e.toString());
            }
        }
        return null;
    }

    public static ArrayList<String> readAsList(File path) {
        if(path.exists()) {
            try {
                return new ArrayList<>(Files.readAllLines(Paths.get(path.toString())));
            } catch(Exception e) {
                Coral.LOGGER.error(e.toString());
            }
        }
        return null;
    }
}
