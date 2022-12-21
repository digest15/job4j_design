package ru.job4j.arhitecture.lsp.examples.animals;

/**
 * Example 2
 * Этот класс нарушает принцип подстановки Лисков:
 * 1. Усиливает предусловия
 */
public class AngryDog extends Animals {
    public AngryDog(String voice) {
        super(voice);
        if (voice.length() < 10) {
            throw new IllegalArgumentException("AngryDog must have voice more than 5 symbols");
        }
    }

    @Override
    public String walk() {
        return "run";
    }
}
