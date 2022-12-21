package ru.job4j.arhitecture.lsp.examples.animals;

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
