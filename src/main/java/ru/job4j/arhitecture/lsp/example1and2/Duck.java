package ru.job4j.arhitecture.lsp.example1and2;

/**
 * Example 2
 * Этот класс нарушает принцип подстановки Лисков
 * так как определяет новое поведение - новый публичный метод
 */
public class Duck extends Animals {
    public Duck(String voice) {
        super(voice);
    }

    @Override
    public String walk() {
        return "walk";
    }

    public String swim() {
        return "bulk bulk";
    }
}
