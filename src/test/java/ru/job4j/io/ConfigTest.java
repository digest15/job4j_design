package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPropertyEntryCase1() {
        String path = "./data/wrong_property_entry_case1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPropertyEntryCase2() {
        String path = "./data/wrong_property_entry_case2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPropertyEntryCase3() {
        String path = "./data/wrong_property_entry_case3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPropertyEntryCase4() {
        String path = "./data/wrong_property_entry_case3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenTwoEqualsSign() {
        String path = "./data/pair_with_two_equals_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value="));
        assertThat(config.value("key1"), is("value=1"));
    }
}