package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static final String PATH = "path";
    private static final String DELIMITER = "delimiter";
    private static final String FILTER = "filter";

    private static final String OUT = "out";

    private static final String STD_OUT = "stdout";

    public static void handle(ArgsName args) throws Exception {
        validateParameters(args);

        int[] destination = buildDestination(args);
        OutputStream writer = builtOutputStream(args);
        if (destination.length > 0) {
            try (Scanner scanner = new Scanner(Path.of(args.get(PATH)));
                 OutputStream out = new BufferedOutputStream(writer)) {
                String delimiter = args.get(DELIMITER);
                while (scanner.hasNextLine()) {
                    String[] columns = scanner.nextLine().split(delimiter);
                    String[] result = new String[destination.length];
                    for (int i : destination) {
                        result[i] = columns[i];
                    }
                    String outStr = String.join(delimiter, result) + System.lineSeparator();
                    out.write(outStr.getBytes());
                }
            }
        } else {
            throw new IllegalStateException("CSV-file don't have columns from filter");
        }
    }

    private static void validateParameters(ArgsName args) {
        var path = args.get(PATH);
        var delimiter  = args.get(DELIMITER);
        var filter = args.get(FILTER);
        var output = args.get(OUT);

        try {
            if (!Files.exists(Paths.get(path))) {
                throw new IllegalArgumentException(String.format("CSV-file not exist: %s", path));
            }
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException(String.format("Uncorrected file path: %s", path), e);
        }

        if (!STD_OUT.equals(output)) {
            try {
                Paths.get(output);
            } catch (InvalidPathException e) {
                throw new IllegalArgumentException(String.format("Uncorrected file path: %s", output));
            }
        }
    }

    private static OutputStream builtOutputStream(ArgsName args) throws FileNotFoundException {
        String path = args.get(OUT);
        if (STD_OUT.equals(path)) {
            return System.out;
        } else {
            return new FileOutputStream(path);
        }
    }

    private static int[] buildDestination(ArgsName args) throws Exception {
        int[] result = new int[0];

        String filter = args.get(FILTER);
        String delimiter = args.get(DELIMITER);

        try (Scanner scanner = new Scanner(Path.of(args.get(PATH)))) {
            if (scanner.hasNextLine()) {
                List<String> columns = Arrays.asList(scanner.nextLine().split(delimiter));
                String[] names = filter.split(",");

                int[] destination = new int[names.length];
                for (int i = 0; i < names.length; i++) {
                    int j = columns.indexOf(names[i]);
                    if (j >= 0) {
                        destination[i] = j;
                    }
                }
                scanner.reset();
                result = destination;
            }
        }
        return result;
    }
}
