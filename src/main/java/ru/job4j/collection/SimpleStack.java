package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> list = new ForwardLinked<T>();

    public T pop() {
        return list.deleteFirst();
    }

    public void push(T value) {
        list.addFirst(value);
    }
}
