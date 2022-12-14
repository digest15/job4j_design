package ru.job4j.arhitecture.lsp.example1and2;

/**
 * Example 2
 * Этот класс нарушает принцип подстановки Лисков
 * Так переопределяет постусловие метода наследника
 * Так же по сути нарушает инвариант базового класса
 */
public class Bug extends Animals {

    public Bug() {
        super(" ");
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
