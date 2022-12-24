package ru.job4j.arhitecture.lsp.protuctstore.expiration;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class LocalDateExpirationCalculatorTest {
    @Test
    public void whenNormalExpiration() {
        double expected = 50.0;
        LocalDate create = LocalDate.of(2022, 1, 1);
        LocalDate expiration = LocalDate.of(2022, 1, 3);
        LocalDate now = LocalDate.of(2022, 1, 2);
        LocalDateExpirationCalculator expirationCalculator = new LocalDateExpirationCalculator();
        assertThat(expirationCalculator.calculateInPercent(
                create,
                expiration,
                now
        )).isEqualTo(expected);
    }

    @Test
    public void whenExpirationEarlyThanCreateThenThrow() {
        LocalDate create = LocalDate.of(2022, 1, 1);
        LocalDate expiration = LocalDate.of(2021, 1, 3);
        LocalDate now = LocalDate.of(2022, 1, 2);
        LocalDateExpirationCalculator expirationCalculator = new LocalDateExpirationCalculator();
        assertThatThrownBy(() -> expirationCalculator.calculateInPercent(
                create,
                expiration,
                now
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNowEarlyThanCreateThenThrow() {
        LocalDate create = LocalDate.of(2022, 1, 1);
        LocalDate expiration = LocalDate.of(2022, 1, 3);
        LocalDate now = LocalDate.of(2021, 1, 2);
        LocalDateExpirationCalculator expirationCalculator = new LocalDateExpirationCalculator();
        assertThatThrownBy(() -> expirationCalculator.calculateInPercent(
                create,
                expiration,
                now
        )).isInstanceOf(IllegalArgumentException.class);
    }
}