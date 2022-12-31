package ru.job4j.arhitecture.lsp.parking;

import java.util.Set;

public interface Parking extends Iterable<Machine> {
    boolean park(Machine auto);

    boolean unpark(Machine auto);

    Set<Machine> getMachines();

    ParkingInfo getParkingInfo();

    class ParkingInfo {

        private final int parkedMachineCount;

        private final int availableCarSpaceCount;

        private final int availableTruckSpaceCount;

        public ParkingInfo(int parkedMachineCount, int availableCarSpaceCount, int availableTruckSpaceCount) {
            this.parkedMachineCount = parkedMachineCount;
            this.availableCarSpaceCount = availableCarSpaceCount;
            this.availableTruckSpaceCount = availableTruckSpaceCount;
        }

        public int getParkedMachineCount() {
            return parkedMachineCount;
        }

        public int getAvailableCarSpaceCount() {
            return availableCarSpaceCount;
        }

        public int getAvailableTruckSpaceCount() {
            return availableTruckSpaceCount;
        }

    }
}
