package ru.job4j.tdd.template;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratorImplTest {
    @Test
    public void whenAllIsFine() {
        String expecting = "I am a Petr Arsentev, Who are you?";
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        String actual = new GeneratorImpl().produce(
                "I am a ${name}, Who are ${subject}?",
                map
        );
        assertThat(actual).isEqualTo(expecting);
    }

    @Test
    public void whenProduceWithEmptyMapThenGetException() {
        assertThrows(
                Exception.class,
                () -> new GeneratorImpl().produce(
                        "I am a ${name}, Who are ${subject}?",
                        Map.of()
                )
        );
    }

    @Test
    public void whenNoKeysInMapThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThrows(
                Exception.class,
                () -> new GeneratorImpl().produce(
                        "I am a ${name}, Who are ${subject}?",
                        map
                )
        );
    }

    @Test
    public void whenNoKeysInTemplateThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThrows(
                Exception.class,
                () -> new GeneratorImpl().produce(
                        "Who are ${subject}?",
                        map
                )
        );
    }

    @Test
    public void whenTemplateIsEmptyGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThrows(
                Exception.class,
                () -> new GeneratorImpl().produce(
                        "",
                        map
                )
        );
    }
}