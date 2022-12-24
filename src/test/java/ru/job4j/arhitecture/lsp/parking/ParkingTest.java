package ru.job4j.arhitecture.lsp.parking;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingTest {
    @Test
    public void whenParkLightAutoAndLightSocketIsAvailable() {
        Parking parking = new ParkingImpl(1, 1);
        assertThat(parking.park(() -> 1)).isTrue();
    }

    @Test
    public void whenParkLightAutoAndLightSocketNotAvailable() {
        Parking parking = new ParkingImpl(0, 1);
        assertThat(parking.park(() -> 1)).isFalse();
    }

    @Test
    public void whenParkHeavyAutoAndHeavySocketIsAvailable() {
        Parking parking = new ParkingImpl(0, 1);
        assertThat(parking.park(() -> 2)).isTrue();
    }

    @Test
    public void whenParkHeavyAutoAndHeavySocketNotAvailableButLightSocketIsAvailable() {
        Parking parking = new ParkingImpl(2, 0);
        assertThat(parking.park(() -> 2)).isTrue();
    }

    @Test
    public void whenParkHeavyAutoAndAnySocketsNotAvailable() {
        Parking parking = new ParkingImpl(1, 0);
        assertThat(parking.park(() -> 2)).isFalse();
    }

    @Test
    public void whenAddTwoItMustBeContainsTwo() {
        int expected = 2;
        Parking parking = new ParkingImpl(1, expected - 1);
        for (int i = 1; i <= expected; i++) {
            int j = i;
            parking.park(() -> j);
        }
        assertThat(parking.size()).isEqualTo(expected);
        assertThat(parking.getPark().size()).isEqualTo(expected);
    }

    @Test
    public void whenParkingSizeIsZero() {
        Parking parking = new ParkingImpl(1, 1);
        assertThatThrownBy(() -> parking.park(() -> 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenUnpark() {
        Parked parked1 = () -> 1;
        Parked parked2 = () -> 1;
        Parking parking = new ParkingImpl(1, 1);
        parking.park(parked1);

        assertThat(parking.unpark(parked1))
                .isTrue();
        assertThat(parking.unpark(parked2))
                .isFalse();
    }

    @Test
    public void whenParkItMustContainsInParkset() {
        Parked parked1 = () -> 1;
        Parked parked2 = () -> 1;
        Parking parking = new ParkingImpl(1, 1);
        parking.park(parked1);

        assertThat(parking.getPark())
                .contains(parked1);
        assertThat(parking.getPark())
                .doesNotContain(parked2);
    }
}