package ru.job4j.tdd.template;

import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
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
        assertThatThrownBy(
                () -> new GeneratorImpl().produce(
                        "I am a ${name}, Who are ${subject}?",
                        Map.of()
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoKeysInMapThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThatThrownBy(
                () -> new GeneratorImpl().produce(
                        "I am a ${name}, Who are ${subject}?",
                        map
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoKeysInTemplateThenGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThatThrownBy(
                () -> new GeneratorImpl().produce(
                        "Who are ${subject}?",
                        map
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateIsEmptyGetException() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev"
        );
        assertThatThrownBy(
                () -> new GeneratorImpl().produce(
                        "",
                        map
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}