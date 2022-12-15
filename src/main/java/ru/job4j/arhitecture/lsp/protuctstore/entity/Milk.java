package ru.job4j.arhitecture.lsp.protuctstore.entity;

import java.time.LocalDate;

public class Milk extends Food {
    @Override
    public double culculateExpiration() {
        long daysForExpiration = expiryDate.toEpochDay() - createDate.toEpochDay();
        long daysFromCreated = LocalDate.now().toEpochDay() - createDate.toEpochDay() - 1;
        return ((double) daysFromCreated / daysForExpiration) * 100;
    }
}
