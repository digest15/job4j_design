package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {

            boolean isStart = false;
            String str;
            while ((str = in.readLine()) != null) {
                if (str.startsWith("200") || str.startsWith("300")) {
                    if (isStart) {
                        String[] strings = str.split(" ");
                        out.print(strings[1] + ";");
                        out.println();
                        isStart = false;
                    }
                } else if (!isStart) {
                    String[] strings = str.split(" ");
                    out.print(strings[1] + ";");
                    isStart = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy.unavailable("./data/server1.log", "./data/unavailable1.csv");
        Analizy.unavailable("./data/server2.log", "./data/unavailable2.csv");
    }
}
