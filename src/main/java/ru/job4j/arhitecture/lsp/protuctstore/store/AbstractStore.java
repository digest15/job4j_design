package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Expiring;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Pricing;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractStore<E extends Expiring & Pricing> implements Store<E> {

    private ArrayList<E> storeds = new ArrayList<>();

    protected abstract boolean isStored(E value);

    @Override
    public boolean add(E value) {
        boolean isAdded = false;
        if (value != null && isStored(value)) {
            storeds.add(value);
            isAdded = true;
        }
        return isAdded;
    }

    @Override
    public boolean remove(E value) {
        return storeds.remove(value);
    }

    @Override
    public int size() {
        return storeds.size();
    }

    @Override
    public Iterator<E> iterator() {
        return storeds.iterator();
    }
}
