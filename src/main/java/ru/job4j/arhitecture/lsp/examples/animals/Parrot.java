package ru.job4j.arhitecture.lsp.examples.animals;

/**
 * Example 3
 * Этот класс нарушает принцип подстановки Лисков:
 * 1. Нарушает исторические ограничения - добавил новый мутатор для поля
 */
public class Parrot extends Animals {
    public Parrot(String voice) {
        super(voice);
    }

    @Override
    public String walk() {
        return "prig prig";
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
