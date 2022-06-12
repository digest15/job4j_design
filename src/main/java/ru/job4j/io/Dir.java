package ru.job4j.io;

import java.io.File;

public class Dir {
    private static final String FILE_PATH = "./data";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s bytes", file.getTotalSpace()));
        for (File subfile : file.listFiles(f -> !f.isDirectory())) {
            System.out.println(String.format("file: %s : size : %s bytes", subfile.getAbsoluteFile(), file.length()));
        }
    }
}
