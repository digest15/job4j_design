package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("You must get two parameters. First: root folder. Second: file extension. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION.");
        }
        String rootFolder = args[0];
        String extension = args[1];

        Path start = Paths.get(rootFolder);
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static class SearchFiles extends SimpleFileVisitor<Path> {
        private List<Path> paths;
        private Predicate<Path> condition;

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
            paths = new ArrayList<>();
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (condition.test(file)) {
                paths.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        public List<Path> getPaths() {
            return paths;
        }
    }
}
