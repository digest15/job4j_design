package ru.job4j.arhitecture.lsp.example1and2;

public class Dog extends Animals {
    public Dog(String voice) {
        super(voice);
    }

    @Override
    public String walk() {
        return "run";
    }
}
