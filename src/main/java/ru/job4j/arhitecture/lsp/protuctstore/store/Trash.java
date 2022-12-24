package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Expiring;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Pricing;

import java.time.LocalDate;

public class Trash<E extends Expiring & Pricing> extends AbstractStore<E> {
    @Override
    protected boolean isStored(E value) {
        return value.calculateExpiration(LocalDate.now()) >= 75.0;
    }
}
