package ru.job4j.set;

public interface Set<E> extends Iterable<E> {
    boolean add(E value);
    boolean contains(E value);
}
