package ru.job4j.arhitecture.lsp.parking;

import java.util.*;

public class ParkingImpl implements Parking {

    private final EnumMap<SocketType, Integer> socketsMap;
    private final HashMap<Machine, SocketType> machines;

    public ParkingImpl(int lightSockets, int heavySockets) {
        socketsMap = new EnumMap<>(SocketType.class);
        socketsMap.put(SocketType.LIGHT, lightSockets);
        socketsMap.put(SocketType.HEAVY, heavySockets);
        machines = new HashMap<>();
    }

    @Override
    public boolean park(Machine auto) {
        int size = auto.parkSize();
        if (size == 0) {
            throw new IllegalArgumentException("Park size can't be 0, auto: " + auto);
        }

        boolean isPark = false;

        if (!machines.containsKey(auto)) {
            if (size == 1) {
                isPark = parkLightAuto(auto, size);
            } else {
                isPark = parkHeavyAuto(auto, size);
            }
        }
        return isPark;
    }

    @Override
    public boolean unpark(Machine auto) {
        int parkSize = auto.parkSize();
        if (parkSize == 0) {
            throw new IllegalArgumentException("Park size can't be 0, auto: " + auto);
        }

        SocketType removeType = machines.remove(auto);
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
    public ParkingInfo getParkingInfo() {
        return new ParkingInfo(
                machines.size(),
                socketsMap.get(SocketType.LIGHT),
                socketsMap.get(SocketType.HEAVY)
        );
    }

    @Override
    public Iterator<Machine> iterator() {
        return machines.keySet().iterator();
    }

    @Override
    public Set<Machine> getMachines() {
        return new HashSet<>(machines.keySet());
    }

    private boolean parkHeavyAuto(Machine auto, int size) {
        boolean isPark;
        int available = socketsMap.get(SocketType.HEAVY);
        if (available > 0) {
            machines.put(auto, SocketType.HEAVY);
            available--;
            socketsMap.put(SocketType.HEAVY, available);
            isPark = true;
        } else {
            isPark = parkLightAuto(auto, size);
        }
        return isPark;
    }

    private boolean parkLightAuto(Machine auto, int size) {
        boolean isPark = false;
        int available = socketsMap.get(SocketType.LIGHT);
        if (available >= size) {
            machines.put(auto, SocketType.LIGHT);
            available = available - size;
            socketsMap.put(SocketType.LIGHT, available);
            isPark = true;
        }
        return isPark;
    }

    private enum SocketType { LIGHT, HEAVY }

}
