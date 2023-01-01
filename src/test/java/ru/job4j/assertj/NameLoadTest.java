package ru.job4j.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    @DisplayName("When parse empty array")
    void whenParseEmptyArray() {
        String[] names = {};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    @DisplayName("When name not contains =")
    void whenNameNotContainsEqual() {
        var name = "WithoutEqual";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);
    }

    @Test
    @DisplayName("When name starts with = (not contain key)")
    void whenNameStartsWithEqual() {
        var name = "=Name";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);
    }

    @Test
    @DisplayName("When name ends with = (not contain value)")
    void whenNameEndsWithEqual() {
        var name = "Name=";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);
    }

    @Test
    @DisplayName("When get empty Map")
    void whenGetEmptyMap() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class);
    }
}