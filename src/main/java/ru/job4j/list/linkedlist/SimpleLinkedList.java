package ru.job4j.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;

    private int modCount;

    private Node<E> head;

    private Node<E> tail;

    /**
     * Constructs an empty linked list.
     */
    public SimpleLinkedList() {
        this.size = 0;
        this.modCount = 0;
        this.head = null;
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param value element to be added to this list
     */
    @Override
    public void add(E value) {
        modCount++;

        Node<E> newNode = new Node<>(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if wrong index, 0 > index >= size
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;

            private Node<E> currentNode = head;

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
                return currentNode != null;
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
                E value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }

        };
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }
}
