package ru.job4j.tdd.template;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneratorImplTest {
    @Test
    @Disabled
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
    @Disabled
    public void whenProduceWithEmptyMapThenGetException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new GeneratorImpl().produce(
                        "I am a ${name}, Who are ${subject}?",
                        Map.of()
                )
        );
    }

    @Test
    @Disabled
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
    @Disabled
    public void whenNoKeysInTemplateThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new GeneratorImpl().produce(
                        "Who are ${subject}?",
                        map
                )
        );
    }

    @Test
    @Disabled
    public void whenTemplateIsEmptyGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new GeneratorImpl().produce(
                        "",
                        map
                )
        );
    }
}