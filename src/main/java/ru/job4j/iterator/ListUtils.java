package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {

    private ListUtils() {
    }

    /**
     * Inserts the specified element at before the specified position in specified list.
     *
     * @param list where will insert element
     * @param index index at which the specified element is to be inserted
     * @param value element to insert
     *
     * @throws NullPointerException if the specified list is null
     * @throws IndexOutOfBoundsException if 0 > index > list.size()
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.add(index, value);
    }

    /**
     * Inserts the specified element at after the specified position in specified list.
     *
     * @param list where will insert element
     * @param index index at which the specified element is to be inserted
     * @param value element to insert
     *
     * @throws NullPointerException if the specified list is null
     * @throws IndexOutOfBoundsException if 0 > index > list.size()
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.add(index + 1, value);
    }

    /**
     * Removes all the elements of specified list that satisfy the given predicate.
     *
     * @param list where will be removed elements
     * @param filter a predicate which returns {@code true} for elements to be removed
     *
     * @throws NullPointerException if the specified list is null
     * @throws UnsupportedOperationException if elements cannot be removed
     *         from specified list.  Implementations may throw this exception if a
     *         matching element cannot be removed or if, in general, removal is not
     *         supported.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while ((iterator.hasNext())) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Replace all the elements of specified list that satisfy the given predicate.
     *
     * @param list where will be repleced elements
     * @param filter a predicate which returns {@code true} for elements to be repleced
     *
     * @throws NullPointerException if the specified list is null
     * @throws UnsupportedOperationException if list is immutable
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while ((iterator.hasNext())) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Removes all specified elements from specified list
     *
     * @param list where will be removed elements
     * @param elements is source removed elements
     *
     * @throws NullPointerException if the specified list or source of elements is null
     * @throws UnsupportedOperationException  if list is immutable
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        elements.forEach(e  -> removeIf(list, l -> l.equals(e)));
    }
}
