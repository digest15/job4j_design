package ru.job4j.gc.references.cache.menu;

import ru.job4j.gc.references.cache.AbstractCache;
import ru.job4j.gc.references.cache.DirFileCache;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DirCacheEmulator extends AbstractEmulator {
    private static final int READ_FILE = 1;
    private static final int EXIT = 0;
    private static final String MENU_TEXT = READ_FILE + ". Чтобы прочитать файл.\n"
                                            + "0. Для выхода.";
    private static final String FILE_NAME_LABEL = "Введите имя файла:";
    public static final String EXIT_LABEL = "Конец работы";
    private static final String DIR_LABEL = "Укажите директорию:";
    private static final String ERROR_LABEL = "Вы ввели не корректные данные!!!";

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
            System.out.println(MENU_TEXT);
            int userChoice = getUserChoice();
            if (READ_FILE == userChoice) {
                System.out.println(FILE_NAME_LABEL);
                String fileName = scanner.nextLine();
                @SuppressWarnings("unchecked")
                String text = ((AbstractCache<String, String>) cache).get(fileName);
                System.out.println(text);
                System.out.println();
            } else if (EXIT == userChoice) {
                run = false;
                System.out.println(EXIT_LABEL);
            } else {
                System.out.println(ERROR_LABEL);
            }
        }
    }

    private int getUserChoice() {
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | NoSuchElementException e) {
            choice = -1;
        }
        return choice;
    }

    @Override
    protected AbstractCache<String, String> createCache() {
        System.out.println(DIR_LABEL);
        String dirPath = scanner.nextLine();
        return new DirFileCache(dirPath);
    }
}
