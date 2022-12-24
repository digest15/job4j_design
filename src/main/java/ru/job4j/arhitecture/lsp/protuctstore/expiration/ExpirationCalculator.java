package ru.job4j.arhitecture.lsp.protuctstore.expiration;

public interface ExpirationCalculator<T> {
    double calculateInPercent(T begin, T end, T with);
}
