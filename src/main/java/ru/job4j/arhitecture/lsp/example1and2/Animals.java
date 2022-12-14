package ru.job4j.arhitecture.lsp.example1and2;

import java.util.Objects;

public abstract class Animals {
    private final String voice;

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
