package ru.job4j.arhitecture.lsp.protuctstore.discount;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Expiring;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Pricing;

import java.time.LocalDate;

public class ExpirationPercentDiscounter<E extends Pricing & Expiring> implements Discounter<E> {
    @Override
    public double calculate(E value) {
        double percentDiscount = 100.0 - value.calculateExpiration(LocalDate.now());
        return value.getPrice() * (percentDiscount / 100);
    }
}
