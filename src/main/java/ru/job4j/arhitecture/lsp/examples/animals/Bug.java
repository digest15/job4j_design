package ru.job4j.arhitecture.lsp.examples.animals;

/**
 * Example 1
 * Этот класс нарушает принцип подстановки Лисков:
 * 1. Переопределяет постусловие в геттере
 * 2. Нарушает предусловие - не берет параметр в конструкторе
 * 3. Так нарушает инвариант базового класса - задает пустое значение голоса
 */
public class Bug extends Animals {

    public Bug() {
        super(" ");
        this.voice = "";
    }

    @Override
    public String getVoice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String walk() {
        return "crawl";
    }
}
