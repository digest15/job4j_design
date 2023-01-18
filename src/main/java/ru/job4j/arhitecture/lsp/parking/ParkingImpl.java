package ru.job4j.arhitecture.lsp.parking;

import java.util.*;

public class ParkingImpl implements Parking {

    private final EnumMap<MachineType, Integer> socketsMap;
    private final HashMap<Machine, MachineType> machines;

    public ParkingImpl(int lightSockets, int heavySockets) {
        socketsMap = new EnumMap<>(MachineType.class);
        socketsMap.put(MachineType.LIGHT, lightSockets);
        socketsMap.put(MachineType.HEAVY, heavySockets);
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
            if (size == Car.SIZE) {
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

        MachineType removeType = machines.remove(auto);
        boolean isUnpark = removeType != null;
        if (isUnpark) {
            int count = socketsMap.get(removeType);
            if (parkSize == Car.SIZE || MachineType.HEAVY.equals(removeType)) {
                count++;
            } else if (MachineType.LIGHT.equals(removeType)) {
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
                socketsMap.get(MachineType.LIGHT),
                socketsMap.get(MachineType.HEAVY)
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
        int available = socketsMap.get(MachineType.HEAVY);
        if (available > 0) {
            machines.put(auto, MachineType.HEAVY);
            available--;
            socketsMap.put(MachineType.HEAVY, available);
            isPark = true;
        } else {
            isPark = parkLightAuto(auto, size);
        }
        return isPark;
    }

    private boolean parkLightAuto(Machine auto, int size) {
        boolean isPark = false;
        int available = socketsMap.get(MachineType.LIGHT);
        if (available >= size) {
            machines.put(auto, MachineType.LIGHT);
            available = available - size;
            socketsMap.put(MachineType.LIGHT, available);
            isPark = true;
        }
        return isPark;
    }

    private enum MachineType { LIGHT, HEAVY }

}
