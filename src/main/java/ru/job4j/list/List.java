package ru.job4j.list;

public interface List<E> extends Iterable<E> {

    void add(E value);

    E set(int index, E newValue);

    E remove(int index);

    E get(int index);

    int size();
}
