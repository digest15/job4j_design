package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Stored;

public interface Store<E extends Stored> extends Iterable<E> {
    boolean add(E value);

    boolean remove(E value);

    int size();
}
