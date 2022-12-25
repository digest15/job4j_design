package ru.job4j.arhitecture.lsp.parking;

import java.util.*;

public class ParkingImpl implements Parking {

    private final EnumMap<SocketType, Integer> socketsMap;
    private final HashMap<Parked, SocketType> park;

    public ParkingImpl(int lightSockets, int heavySockets) {
        socketsMap = new EnumMap<>(SocketType.class);
        socketsMap.put(SocketType.LIGHT, lightSockets);
        socketsMap.put(SocketType.HEAVY, heavySockets);
        park = new HashMap<>();
    }

    @Override
    public Boolean park(Parked auto) {
        int size = auto.parkSize();
        if (size == 0) {
            throw new IllegalArgumentException("Park size can't be 0, auto: " + auto);
        }

        Boolean isPark = Boolean.FALSE;

        if (!park.containsKey(auto)) {
            if (size == 1) {
                isPark = parkLightAuto(auto, size);
            } else {
                isPark = parkHeavyAuto(auto, size);
            }
        }
        return isPark;
    }

    @Override
    public Boolean unpark(Parked auto) {
        int parkSize = auto.parkSize();
        if (parkSize == 0) {
            throw new IllegalArgumentException("Park size can't be 0, auto: " + auto);
        }

        SocketType removeType = park.remove(auto);
        boolean isUnpark = removeType != null;
        if (isUnpark) {
            int count = socketsMap.get(removeType);
            if (parkSize == 1 || SocketType.HEAVY.equals(removeType)) {
                count++;
            } else if (SocketType.LIGHT.equals(removeType)) {
                count = count + parkSize;
            }
            socketsMap.put(removeType, count);
        }
        return isUnpark;
    }

    @Override
    public Iterator<Parked> iterator() {
        return park.keySet().iterator();
    }

    @Override
    public int size() {
        return park.size();
    }

    @Override
    public Set<Parked> getPark() {
        return new HashSet<>(park.keySet());
    }

    @Override
    public int getLightAvailableSockets() {
        return socketsMap.get(SocketType.LIGHT);
    }

    @Override
    public int getHighAvailableSockets() {
        return socketsMap.get(SocketType.HEAVY);
    }

    private Boolean parkHeavyAuto(Parked auto, int size) {
        Boolean isPark;
        int available = socketsMap.get(SocketType.HEAVY);
        if (available > 0) {
            park.put(auto, SocketType.HEAVY);
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
            park.put(auto, SocketType.LIGHT);
            available = available - size;
            socketsMap.put(SocketType.LIGHT, available);
            isPark = Boolean.TRUE;
        }
        return isPark;
    }

    private enum SocketType { LIGHT, HEAVY }

}
