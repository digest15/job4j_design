package ru.job4j.gc.references.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner text = new StringJoiner(System.lineSeparator());
        try {
            Files.lines(Paths.get(cachingDir.concat(key)))
                    .forEach(text::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}
