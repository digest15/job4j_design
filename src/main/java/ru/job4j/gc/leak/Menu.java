package ru.job4j.gc.leak;

import ru.job4j.gc.leak.generators.CommentGenerator;
import ru.job4j.gc.leak.generators.UserGenerator;
import ru.job4j.gc.leak.model.Post;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public static final int ADD_POST = 1;
    public static final int ADD_MANY_POST = 2;
    public static final int SHOW_ALL_POSTS = 3;
    public static final int DELETE_POST = 4;

    public static final String SELECT = "Выберите меню";
    public static final String COUNT = "Выберите количество создаваемых постов";
    public static final String TEXT_OF_POST = "Введите текст";
    public static final String EXIT = "Конец работы";

    private static Random random;

    public static final String MENU = "Введите 1 для создание поста.\n"
                + "Введите 2, чтобы создать определенное количество постов.\n"
                + "Введите 3, чтобы показать все посты.\n"
                + "Введите 4, чтобы удалить все посты.\n"
                + "Введите любое другое число для выхода.\n";

    public static void main(String[] args) {
        random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, postStore);
    }

    private static void start(CommentGenerator commentGenerator, Scanner scanner, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                postStore.add(new Post(text, commentGenerator.generate()));
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                System.out.println(COUNT);
                int count = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < count; i++) {
                    createPost(commentGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator, PostStore postStore, String text) {
        postStore.add(new Post(text, commentGenerator.generate()));
    }
}
