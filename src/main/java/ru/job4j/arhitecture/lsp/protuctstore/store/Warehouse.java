package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Stored;

public class Warehouse<E extends Stored> extends AbstractStore<E> {

    @Override
    protected boolean isStored(E value) {
        return value.culculateExpiration() < 25.0;
    }
}
