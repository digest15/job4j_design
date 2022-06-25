package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./data"), new DuplicatesVisitor());
    }

    public static class DuplicatesVisitor extends SimpleFileVisitor<Path> {
        private Path head;
        private final Map<FileProperty, List<Path>> filePath = new HashMap<>();

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (head == null) {
                head = dir;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
            List<Path> paths = filePath.get(fileProperty);
            if (paths == null) {
                paths = new ArrayList<>();
            }
            paths.add(file);
            filePath.put(fileProperty, paths);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if (head.hashCode() == dir.hashCode() && head.equals(dir)) {
                head = null;
                for (Map.Entry<FileProperty, List<Path>> entry : filePath.entrySet()) {
                    List<Path> paths = entry.getValue();
                    if (paths.size() > 1) {
                        System.out.println(entry.getKey());
                        for (Path path : paths) {
                            System.out.println("    " + path.toString());
                        }
                    }
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static class FileProperty {

        private long size;

        private String name;

        public FileProperty(long size, String name) {
            this.size = size;
            this.name = name;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FileProperty that = (FileProperty) o;
            return size == that.size && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(size, name);
        }

        @Override
        public String toString() {
            return String.format("%s - %d byte", name, size);
        }
    }
}
