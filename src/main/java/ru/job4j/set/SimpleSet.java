package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Set<E> {

    private final SimpleArrayList<E> list = new SimpleArrayList<>(5);

    @Override
    public boolean add(E value) {
        boolean is = contains(value);
        if (!is) {
            list.add(value);
        }
        return !is;
    }

    @Override
    public boolean contains(E value) {
        boolean is = false;
        for (E el : list) {
            if (el == value || value.equals(el)) {
                is = true;
                break;
            }
        }
        return is;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
