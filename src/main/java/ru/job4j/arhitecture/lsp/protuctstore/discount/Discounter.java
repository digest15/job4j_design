package ru.job4j.arhitecture.lsp.protuctstore.discount;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Pricing;

public interface Discounter<E extends Pricing> {
    double calculate(E value);
}
