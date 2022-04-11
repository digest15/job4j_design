package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();

    private final SimpleStack<T> out = new SimpleStack<>();

    private int sizeIn = 0;
    private int sizeOut = 0;

    /**
     * Removes the fist element from this queue.
     *
     * @return the first element
     * @throws NoSuchElementException if queue is empty
     */
    public T poll() {
        if (sizeOut == 0) {
            for (int i = 0; i < sizeIn; i++) {
                out.push(in.pop());
                sizeOut++;
            }
            sizeIn = 0;
        }

        sizeOut--;
        return out.pop();
    }

    /**
     * Adds an element to the tail of this queue.
     *
     */
    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
