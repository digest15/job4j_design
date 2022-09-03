package ru.job4j.io.filefinders;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FileLogFileFinder {
    private static final String DIRECTORY_PARAM_NAME = "d";
    private static final String PATTERN_PARAM_NAME = "n";
    private static final String TYPE_PARAM_NAME = "t";
    private static final String LOG_FILE_PARAM_NAME = "o";

    public static void main(String[] args) {
        ArgsName prm = ArgsName.of(args);
        validate(prm);

        Path root = Path.of(prm.get(DIRECTORY_PARAM_NAME));
        String pattern = prm.get(PATTERN_PARAM_NAME);
        Predicate<Path> predicate = FindType.valueOf(prm.get(TYPE_PARAM_NAME).toUpperCase()).getFilter(pattern);

        try {
            List<Path> result = Search.search(root, predicate);
            try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Path.of(prm.get(LOG_FILE_PARAM_NAME))))) {
                for (Path path : result) {
                    pw.println(path.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName args) {
        String d = args.get(DIRECTORY_PARAM_NAME);
        String n = args.get(PATTERN_PARAM_NAME);
        String t = args.get(TYPE_PARAM_NAME).toUpperCase();
        String o = args.get(LOG_FILE_PARAM_NAME);

        Path source = Path.of(d);
        if (!Files.exists(source)) {
            throw new IllegalArgumentException(String.format("Not exist %s", source.toAbsolutePath()));
        }

        Path log = Path.of(o);

        try {
            FindType.valueOf(t);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Wrong type from -t parameter: %s. You must use: %s", t, Arrays.toString(FindType.values())));
        }

    }

    private enum FindType {
        MASK() {
            @Override
            Predicate<Path> getFilter(String pattern) {
                pattern = pattern
                        .replaceAll("\\.", "\\\\.")
                        .replaceAll("\\*", ".*")
                        .replaceAll("\\?", ".?");
                String mask = ".*" + pattern + ".*";
                return path -> path.toFile().getName().matches(mask);
            }
        },
        NAME() {
            @Override
            Predicate<Path> getFilter(String pattern) {
                return path -> path.endsWith(pattern);
            }
        },
        REGEX() {
            @Override
            Predicate<Path> getFilter(String pattern) {
                return path -> path.toFile().getName().matches(pattern);
            }
        };

        abstract Predicate<Path> getFilter(String pattern);
    }
}
