package ru.job4j.gc.leak.generators;

import ru.job4j.gc.leak.model.Comment;
import ru.job4j.gc.leak.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate<Comment> {
    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    public static final String SEPARATOR = System.lineSeparator();
    public static final int COUNT = 50;
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Comment> generate() {
        List<User> users = userGenerator.generate();
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            String comment = String.format("%s%s%s%s%s",
                    phrases.get(random.nextInt(phrases.size())), SEPARATOR,
                    phrases.get(random.nextInt(phrases.size())), SEPARATOR,
                    phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(comment, users.get(random.nextInt(users.size()))));
        }
        return comments;
    }
}
