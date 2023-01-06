package ru.job4j.arhitecture.lsp.parking;

public class Car implements Machine {
    public static final int SIZE = 1;

    @Override
    public int parkSize() {
        return SIZE;
    }
}
