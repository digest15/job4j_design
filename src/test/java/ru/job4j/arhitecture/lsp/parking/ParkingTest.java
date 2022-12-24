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
        Parking parking = new ParkingImpl(3, 1);
        assertThat(parking.park(() -> 3)).isFalse();
    }
}