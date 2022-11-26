package ru.job4j.gc.references.cache.menu;

import ru.job4j.gc.references.cache.AbstractCache;
import ru.job4j.gc.references.cache.DirFileCache;

import java.util.Scanner;

public class DirCacheEmulator extends AbstractEmulator {
    private static final int READ_FILE = 1;
    private static final String MENU = READ_FILE + ". Чтобы прочитать файл.\n"
            + "Введите любое другое число для выхода.";
    private static final String FILE_NAME = "Введите имя файла:";
    public static final String EXIT = "Конец работы";
    private static final String DIR = "Укажите директорию:";

    protected Scanner scanner;

    @Override
    protected void init() {
        scanner = new Scanner(System.in);
        super.init();

    }

    @Override
    protected void menu() {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (READ_FILE == userChoice) {
                System.out.println(FILE_NAME);
                String fileName = scanner.nextLine();
                @SuppressWarnings("unchecked")
                String text = ((AbstractCache<String, String>) cache).get(fileName);
                System.out.println(text);
                System.out.println();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    @Override
    protected AbstractCache<String, String> createCache() {
        System.out.println(DIR);
        String dirPath = scanner.nextLine();
        return new DirFileCache(dirPath);
    }
}
