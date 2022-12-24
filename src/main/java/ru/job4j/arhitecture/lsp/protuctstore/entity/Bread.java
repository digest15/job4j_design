package ru.job4j.arhitecture.lsp.protuctstore.entity;

import ru.job4j.arhitecture.lsp.protuctstore.expiration.ExpirationCalculator;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(ExpirationCalculator<LocalDate> expirationCalculator) {
        super(expirationCalculator);
    }

    @Override
    public double calculateExpiration(LocalDate forDate) {
        return expirationCalculator.calculateInPercent(createDate, expiryDate, forDate);
    }
}
