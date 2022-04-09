package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Removes the fist element from this queue.
     *
     * @return the first element
     * @throws NoSuchElementException if queue is empty
     */
    public T poll() {
        try {
            while (true) {
                out.push(in.pop());
            }
        } catch (NoSuchElementException e) {

        }
        return out.pop();
    }

    /**
     * Adds an element to the tail of this queue.
     *
     */
    public void push(T value) {
        try {
            while (true) {
                in.push(out.pop());
            }
        } catch (NoSuchElementException e) {

        }
        in.push(value);
    }
}
