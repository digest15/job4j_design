package ru.job4j.arhitecture.lsp.parking;

import java.util.*;

public class ParkingImpl implements Parking {

    private final EnumMap<SocketType, Integer> socketsMap;
    private final Set<Parked> park;

    public ParkingImpl(int lightSockets, int heavySockets) {
        socketsMap = new EnumMap<>(SocketType.class);
        socketsMap.put(SocketType.LIGHT, lightSockets);
        socketsMap.put(SocketType.HEAVY, heavySockets);
        park = new HashSet<>();
    }

    @Override
    public Boolean park(Parked auto) {
        int size = auto.parkSize();
        if (size == 0) {
            throw new IllegalArgumentException("Park size can't be 0, auto: " + auto);
        }

        Boolean isPark = Boolean.FALSE;

        if (!park.contains(auto)) {
            if (size == 1) {
                isPark = parkLightAuto(auto, size);
            } else if (size > 1) {
                isPark = parkHeavyAuto(auto, size);
            }
        }
        return isPark;
    }

    @Override
    public Boolean unpark(Parked auto) {
        return park.remove(auto);
    }

    @Override
    public Iterator<Parked> iterator() {
        return park.iterator();
    }

    @Override
    public int size() {
        return park.size();
    }

    @Override
    public Set<Parked> getPark() {
        return new HashSet<>(park);
    }

    private Boolean parkHeavyAuto(Parked auto, int size) {
        Boolean isPark;
        int available = socketsMap.get(SocketType.HEAVY);
        if (available > 0) {
            park.add(auto);
            available--;
            socketsMap.put(SocketType.HEAVY, available);
            isPark = Boolean.TRUE;
        } else {
            isPark = parkLightAuto(auto, size);
        }
        return isPark;
    }

    private Boolean parkLightAuto(Parked auto, int size) {
        Boolean isPark = Boolean.FALSE;
        int available = socketsMap.get(SocketType.LIGHT);
        if (available >= size) {
            park.add(auto);
            available = available - size;
            socketsMap.put(SocketType.LIGHT, available);
            isPark = Boolean.TRUE;
        }
        return isPark;
    }

    private enum SocketType { LIGHT, HEAVY }

}
