package ru.job4j.arhitecture.lsp.protuctstore;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Expiring;
import ru.job4j.arhitecture.lsp.protuctstore.store.Store;

import java.util.List;

public interface ControlQuality<E extends Expiring> {

    void add(E value);

    int redistribution(List<Store<E>> stores);

    int reSort(List<Store<E>> stores);
}
