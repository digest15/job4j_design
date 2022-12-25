package ru.job4j.arhitecture.lsp.parking;

import java.util.Set;

public interface Parking extends Iterable<Parked> {
    Boolean park(Parked auto);

    Boolean unpark(Parked auto);

    int size();

    Set<Parked> getPark();

    int getLightAvailableSockets();

    int getHighAvailableSockets();
}
