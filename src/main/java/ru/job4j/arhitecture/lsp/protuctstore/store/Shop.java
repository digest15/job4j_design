package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Stored;

public class Shop<E extends Stored> extends AbstractStore<E> {
    @Override
    protected boolean isStored(E value) {
        double expiration = value.culculateExpiration();
        return expiration >= 25.0 && expiration < 75.0;
    }
}
