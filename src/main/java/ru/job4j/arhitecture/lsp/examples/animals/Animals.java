package ru.job4j.arhitecture.lsp.examples.animals;

import java.util.Objects;

public abstract class Animals {
    protected String voice;

    public Animals(String voice) {
        if (Objects.isNull(voice) || voice.isEmpty()) {
            throw new IllegalArgumentException("It must have a voice!");
        }
        this.voice = voice;
    }

    public String getVoice() {
        return voice;
    }

    public abstract String walk();
}
