package ru.job4j.arhitecture.condition;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {
    @Test
    public void findMax() {
        List<Integer> list = List.of(1, 1, 2, 3, 4, 5, 5);
        Integer max = MaxMin.max(list, Integer::compare);
        assertThat(max, is(5));
    }

    @Test
    public void findMin() {
        List<Integer> list = List.of(1, 1, 2, 3, 4, 5, 5);
        Integer max = MaxMin.min(list, Integer::compare);
        assertThat(max, is(1));
    }

    @Test
    public void findMinWhenString() {
        List<String> list = List.of("A", "B", "C", "D", "E", "F", "G");
        String max = MaxMin.min(list, String::compareTo);
        assertThat(max, is("A"));
    }

    @Test
    public void findMaxWhenString() {
        List<String> list = List.of("A", "B", "C", "D", "E", "F", "G");
        String max = MaxMin.max(list, String::compareTo);
        assertThat(max, is("G"));
    }
}