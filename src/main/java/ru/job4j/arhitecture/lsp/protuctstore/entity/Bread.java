package ru.job4j.arhitecture.lsp.protuctstore.entity;

import java.time.LocalDate;

public class Bread extends Food {
    @Override
    public double culculateExpiration() {
        long daysForExpiration = expiryDate.toEpochDay() - createDate.toEpochDay();
        long daysFromCreated = LocalDate.now().toEpochDay() - createDate.toEpochDay();
        return ((double) daysFromCreated / daysForExpiration) * 100;
    }
}
