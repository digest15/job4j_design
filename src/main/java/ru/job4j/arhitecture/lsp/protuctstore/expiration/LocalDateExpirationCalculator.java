package ru.job4j.arhitecture.lsp.protuctstore.expiration;

import java.time.LocalDate;

public class LocalDateExpirationCalculator implements ExpirationCalculator<LocalDate> {
    @Override
    public double calculateInPercent(LocalDate begin, LocalDate end, LocalDate with) {
        long daysForExpiration = end.toEpochDay() - begin.toEpochDay();
        long daysFromCreated = with.toEpochDay() - begin.toEpochDay();
        if (daysForExpiration < 0 || daysFromCreated < 0) {
            throw new IllegalArgumentException("Wrong date parameters");
        }
        return ((double) daysFromCreated / daysForExpiration) * 100;
    }
}
