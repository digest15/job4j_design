package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            sources.forEach(path -> {
                String pathStr = path.toString();
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(pathStr))) {
                    zip.putNextEntry(new ZipEntry(pathStr));
                    zip.write(in.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        validate(jvm);

        File source = new File(jvm.get("d"));
        File target = new File(jvm.get("o"));
        String extention = jvm.get("e");

        List<Path> sourses = Search.search(source.toPath(), p -> !p.toString().endsWith(extention));

        Zip zipper = new Zip();
        zipper.packFiles(sourses, target);
    }

    private static void validate(ArgsName args) {
        String d = args.get("d");
        String e = args.get("e");
        String o = args.get("o");

        Path source = Paths.get(d);
        if (!Files.exists(source)) {
            throw new IllegalArgumentException(String.format("Not exist %s", source.toAbsolutePath()));
        }

        if (!e.startsWith(".")) {
            throw new IllegalArgumentException(String.format("Extension must start with \".\". value: %s", e));
        }
    }
}
