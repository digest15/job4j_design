package ru.job4j.gc.leak.generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface Generate<E> {

    List<E> generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        Files.lines(Paths.get(path))
                .forEach(text::add);
        return text;
    }
}
