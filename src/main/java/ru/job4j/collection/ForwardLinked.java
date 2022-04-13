package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Revert this list.
     *
     * * @return <code>true</code> if the revert is done. <code>false</code> if list is empty or have one element
     */
    public boolean revert() {
        boolean notEmpty = (head != null && head.next != null);
        if (notEmpty) {
            Node<T> prev = null;
            Node<T> curr = head;
            while (curr != null) {
                Node<T> next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            head = prev;
        }
        return notEmpty;
    }

    /**
     * Adds an element to head this list.
     *
     */
    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * Removes the first element from this list.
     *
     * @return the first element from the list
     * @throws NoSuchElementException if list is empty
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node<T> buf = head;
        head = head.next;
        T value = buf.value;

        buf.next = null;
        buf.value = null;

        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
