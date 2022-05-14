package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.StringJoiner;

public class MultiplicationTable {
    private static int to = 5;

    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("multitable.txt");
                BufferedOutputStream out = new BufferedOutputStream(fos)) {
            for (int i = 1; i <= to; i++) {
                StringJoiner str = new StringJoiner(" ");
                for (int j = 1; j <= to; j++) {
                    str.add(String.valueOf(i * j));
                }
                str.add(System.lineSeparator());
                out.write(str.toString().getBytes());
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }
}
