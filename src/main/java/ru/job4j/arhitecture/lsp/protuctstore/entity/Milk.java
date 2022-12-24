package ru.job4j.arhitecture.lsp.protuctstore.entity;

import ru.job4j.arhitecture.lsp.protuctstore.expiration.ExpirationCalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Milk extends Food {

    public Milk(ExpirationCalculator<LocalDate> expirationCalculator) {
        super(expirationCalculator);
    }

    @Override
    public double calculateExpiration(LocalDate forDate) {
        return expirationCalculator.calculateInPercent(
                createDate.minus(1, ChronoUnit.DAYS),
                expiryDate,
                forDate
        );
    }
}
