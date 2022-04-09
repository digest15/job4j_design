package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private ForwardLinked<T> list = new ForwardLinked<T>();

    /**
     * Removes the top of this stack.
     *
     * @return the element from top
     * @throws NoSuchElementException if stack is empty
     */
    public T pop() {
        return list.deleteFirst();
    }


    /**
     * Adds an element to the top.
     *
     */
    public void push(T value) {
        list.addFirst(value);
    }
}
