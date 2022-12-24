package ru.job4j.arhitecture.lsp.parking;

public interface Parking extends Iterable<Parked> {
    Boolean park(Parked auto);

    int size();
}
