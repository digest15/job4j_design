package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<E> implements List<E> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] container;

    private int size;

    private int modCount;

    private int capacity;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  capacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified size is negative or more Integer.MAX_VALUE - 8
     */
    public SimpleArrayList(int capacity) {
        if (capacity < 0 | capacity > MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(String.format("Illegal capacity: %s", capacity));
        }

        this.container = new Object[capacity];

        this.size = 0;
        this.modCount = 0;
        this.capacity = capacity;
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param value element to be added to this list
     */
    @Override
    public void add(E value) {
        modCount++;

        if (size == capacity) {
            grow();
        }
        container[size++] = value;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param newValue element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if wrong index, 0 < index > (size() - 1)
     */
    @Override
    public E set(int index, E newValue) {
        Objects.checkIndex(index, container.length);
        modCount++;

        @SuppressWarnings("unchecked") E oldValue = (E) container[index];
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if wrong index, 0 < index > (size() - 1)
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, container.length);
        modCount++;

        @SuppressWarnings("unchecked") E value = (E) container[index];
        if ((--size) > index) {
            System.arraycopy(container, index + 1, container, index, size - 1);
        }
        container[size] = null;
        return value;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this container
     * @throws IndexOutOfBoundsException if wrong index, 0 < index > (size() - 1)
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        Objects.checkIndex(index, container.length);
        return (E) container[index];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private int cursor = 0;

            /**
             * {@inheritDoc}
             *
             * @throws ConcurrentModificationException for <a href="#fail-fast"><i>fail-fast</i></a>.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            /**
             * {@inheritDoc}
             *
             * @throws ConcurrentModificationException for <a href="#fail-fast"><i>fail-fast</i></a>.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                @SuppressWarnings("unchecked")
                E value = (E) container[cursor++];
                return value;
            }

        };
    }

    private void grow() {
        int newCapacity = capacity * 2 + 1;
        container = Arrays.copyOf(container, newCapacity);
        capacity = newCapacity;
    }
}
