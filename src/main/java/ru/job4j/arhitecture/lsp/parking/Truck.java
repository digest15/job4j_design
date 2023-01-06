package ru.job4j.arhitecture.lsp.parking;

public class Truck implements Machine {
    private final int size;

    public Truck(int size) {
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("Invalid truck size. Size must be more than 1");
        }
        this.size = size;
    }

    @Override
    public int parkSize() {
        return size;
    }
}
