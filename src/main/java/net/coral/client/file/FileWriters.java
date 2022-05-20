package net.coral.client.file;

import net.coral.client.Coral;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWriters {
    public static void writeFile(File path, ArrayList<String> data) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(path);
            for(String str : data) {
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            Coral.LOGGER.error(e.toString());
        }
    }

    public static void writeFile(File path, String[] data) {
        ArrayList<String> array = new ArrayList<>(Arrays.asList(data));
        try {
            java.io.FileWriter fw = new java.io.FileWriter(path);
            for(String str : array) {
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            Coral.LOGGER.error(e.toString());
        }
    }

    public static void writeFile(File path, String data) {
        try {
            Files.write(path.toPath(), data.getBytes());
        } catch(IOException e) {
            Coral.LOGGER.error(e.toString());
        }
    }

    public static void writeFile(File path, String data, String regex) {
        ArrayList<String> array = new ArrayList<>(Arrays.asList(data.split(regex)));
        try {
            java.io.FileWriter fw = new java.io.FileWriter(path);
            for(String str : array) {
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            Coral.LOGGER.error(e.toString());
        }
    }
}
