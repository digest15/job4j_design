package ru.job4j.io;

import java.io.File;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("even.txt"))) {
            while (in.hasNextInt()) {
                int read = in.nextInt();
                if (read % 2 == 0) {
                    System.out.println(read);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
