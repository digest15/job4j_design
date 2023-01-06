package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Expiring;

import java.util.List;

public interface Store<E extends Expiring> extends Iterable<E> {
    boolean add(E value);

    boolean remove(E value);

    int size();

    List<E> clear();
}
