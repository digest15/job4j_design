package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.hamcrest.Matchers.is;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable1() throws IOException {
        test("./data/server1.log", "./data/unavailable_expected_1.csv");
    }

    @Test
    public void unavailable2() throws IOException {
        test("./data/server2.log", "./data/unavailable_expected_2.csv");
    }

    private void test(String logPath, String analizyPath) throws IOException {
        File target = folder.newFile("target.txt");
        Analizy.unavailable(logPath, target.getAbsolutePath());

        String expected = getStringFromFile(analizyPath);
        String actual = getStringFromFile(target.getAbsolutePath());
        assertThat(actual, is(expected));
    }

    private String getStringFromFile(String path) throws IOException {
        String rls;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            rls = in.lines().collect(Collectors.joining(" "));
        }
        return rls;
    }
}