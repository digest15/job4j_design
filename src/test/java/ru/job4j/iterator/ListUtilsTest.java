package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, -1, 0);
        ListUtils.addAfter(input, 2, 0);
    }

    @Test
    public void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        ListUtils.removeIf(input, e -> e.equals(2));
        assertThat(input, is(Arrays.asList(1, 1)));
    }

    @Test
    public void whenRemoveFromEmptyList() {
        List<Integer> input = new ArrayList<>();
        ListUtils.removeIf(input, e -> e.equals(2));
        assertThat(input, is(new ArrayList<>()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenCannotRemove() {
        List<Integer> input = List.of(1, 2);
        ListUtils.removeIf(input, e -> e.equals(2));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        ListUtils.replaceIf(input, e -> e.equals(2), 3);
        assertThat(input, is(Arrays.asList(1, 3, 1, 3)));
    }

    @Test
    public void whenReplaceInEmptyList() {
        List<Integer> input = new ArrayList<>();
        ListUtils.replaceIf(input, e -> e.equals(2), 1);
        assertThat(input, is(new ArrayList<>()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenCannotReplace() {
        List<Integer> input = List.of(1, 2);
        ListUtils.replaceIf(input, e -> e.equals(2), 3);
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 4));
        ListUtils.removeAll(input, List.of(2, 4));
        assertThat(input, is(Arrays.asList(1, 1, 3)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenCannotRemoveAll() {
        List<Integer> input = List.of(1, 2);
        ListUtils.removeAll(input, List.of(2, 4));
    }

}