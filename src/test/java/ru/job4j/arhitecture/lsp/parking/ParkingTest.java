package ru.job4j.arhitecture.lsp.parking;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingTest {
    @Test
    public void whenParkCarAndLightSocketIsAvailable() {
        Parking parking = new ParkingImpl(1, 1);
        Machine car = () -> 1;
        assertThat(parking.park(car)).isTrue();
    }

    @Test
    public void whenParkCarAndLightSocketNotAvailable() {
        Parking parking = new ParkingImpl(0, 1);
        Machine car = () -> 1;
        assertThat(parking.park(car)).isFalse();
    }

    @Test
    public void whenParkTrackAndHeavySocketIsAvailable() {
        Parking parking = new ParkingImpl(0, 1);
        Machine track = () -> 2;
        assertThat(parking.park(track)).isTrue();
    }

    @Test
    public void whenParkTrackAndHeavySocketNotAvailableButLightSocketIsAvailable() {
        Parking parking = new ParkingImpl(2, 0);
        Machine track = () -> 2;
        assertThat(parking.park(track)).isTrue();
    }

    @Test
    public void whenParkTrackAndAnySocketsNotAvailable() {
        Parking parking = new ParkingImpl(1, 0);
        Machine track = () -> 2;
        assertThat(parking.park(track)).isFalse();
    }

    @Test
    public void whenParkCarItMustReduceLightSocketsCount() {
        Machine car = () -> 1;
        int startSockets = 2;
        int expectedSockets = 1;
        Parking parking = new ParkingImpl(startSockets, 0);

        assertThat(parking.getParkingInfo().getAvailableCarSpaceCount()).isEqualTo(startSockets);
        parking.park(car);
        assertThat(parking.getParkingInfo().getAvailableCarSpaceCount()).isEqualTo(expectedSockets);
    }

    @Test
    public void whenParkTrackItMustReduceHighSocketsCount() {
        Machine track = () -> 2;
        int startSockets = 2;
        int expectedSockets = 1;
        Parking parking = new ParkingImpl(0, startSockets);

        assertThat(parking.getParkingInfo().getAvailableTruckSpaceCount()).isEqualTo(startSockets);
        parking.park(track);
        assertThat(parking.getParkingInfo().getAvailableTruckSpaceCount()).isEqualTo(expectedSockets);
    }

    @Test
    public void whenParkTrackOnLightSocketsItMustReduceLightSocketsCount() {
        Machine track = () -> 2;
        int startSockets = 3;
        int expectedSockets = 1;
        Parking parking = new ParkingImpl(startSockets, 0);

        assertThat(parking.getParkingInfo().getAvailableCarSpaceCount()).isEqualTo(startSockets);
        parking.park(track);
        assertThat(parking.getParkingInfo().getAvailableCarSpaceCount()).isEqualTo(expectedSockets);
    }

    @Test
    public void whenUnparkCarInMustIncreaseLightSocketsCount() {
        Machine car = () -> 1;
        int expectedSockets = 2;
        Parking parking = new ParkingImpl(expectedSockets, 0);
        parking.park(car);
        parking.unpark(car);
        assertThat(parking.getParkingInfo().getAvailableCarSpaceCount()).isEqualTo(expectedSockets);
    }

    @Test
    public void whenUnparkTrackInMustIncreaseHighSocketsCount() {
        Machine track = () -> 2;
        int expectedSockets = 2;
        Parking parking = new ParkingImpl(0, expectedSockets);
        parking.park(track);
        parking.unpark(track);
        assertThat(parking.getParkingInfo().getAvailableTruckSpaceCount()).isEqualTo(expectedSockets);
    }

    @Test
    public void whenUnparkTrackFromLightSocketsItMustIncreaseLightSocketsCount() {
        Machine track = () -> 2;
        int expectedSockets = 3;
        Parking parking = new ParkingImpl(expectedSockets, 0);
        parking.park(track);
        parking.unpark(track);
        assertThat(parking.getParkingInfo().getAvailableCarSpaceCount()).isEqualTo(expectedSockets);
    }

    @Test
    public void whenAddTwoItMustBeContainsTwo() {
        int expected = 2;
        Parking parking = new ParkingImpl(1, expected - 1);
        for (int i = 1; i <= expected; i++) {
            int j = i;
            Machine machine = () -> j;
            parking.park(machine);
        }
        assertThat(parking.getParkingInfo().getParkedMachineCount()).isEqualTo(expected);
        assertThat(parking.getMachines().size()).isEqualTo(expected);
    }

    @Test
    public void whenParkAndSizeIsZero() {
        Parking parking = new ParkingImpl(1, 1);
        Machine wrongMachine = () -> 0;
        assertThatThrownBy(() -> parking.park(wrongMachine))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenUnpark() {
        Machine car1 = () -> 1;
        Machine car2 = () -> 1;
        Parking parking = new ParkingImpl(1, 1);
        parking.park(car1);

        assertThat(parking.unpark(car1))
                .isTrue();
        assertThat(parking.unpark(car2))
                .isFalse();
    }

    @Test
    public void whenUnparkAndSizeIsZero() {
        Parking parking = new ParkingImpl(1, 1);
        Machine car = () -> 0;
        assertThatThrownBy(() -> parking.unpark(car))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenParkItMustContainsInParkset() {
        Machine car1 = () -> 1;
        Machine car2 = () -> 1;
        Parking parking = new ParkingImpl(1, 1);
        parking.park(car1);

        assertThat(parking.getMachines())
                .contains(car1);
        assertThat(parking.getMachines())
                .doesNotContain(car2);
    }
}