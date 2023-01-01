package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                /*.containsAnyOf("zero", "second", "six")*/
                .doesNotContain("first", Index.atIndex(1))
                .allMatch(s -> s.length() < 10);
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> strings = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(strings)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .doesNotContain("first", Index.atIndex(1))
                .allMatch(s -> s.length() < 10);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .containsKey("second")
                .containsValue(2)
                .containsEntry("four", 3)
                .doesNotContain(Map.entry("four", 4))
                .contains(Map.entry("first", 0))
                .containsValues(0, 1, 2);
    }
}