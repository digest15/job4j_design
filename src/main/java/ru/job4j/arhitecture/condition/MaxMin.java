package ru.job4j.arhitecture.condition;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntPredicate;

public class MaxMin {
    private static IntPredicate maxPredicate = i -> i < 0;

    public static  <T> T max(List<T> list, Comparator<T> comparator) {
        return findBy(list, comparator, maxPredicate);
    }

    public static <T> T min(List<T> list, Comparator<T> comparator) {
        return findBy(list, comparator, maxPredicate.negate());
    }

    private static <T> T findBy(List<T> list, Comparator<T> comparator, IntPredicate predicate) {
        Iterator<? extends T> i = list.iterator();
        T candidate = null;
        if (i.hasNext()) {
            candidate = i.next();
        }

        while (i.hasNext()) {
            T next = i.next();
            int comp = comparator.compare(candidate, next);
            if (predicate.test(comp)) {
                candidate = next;
            }
        }
        return candidate;
    }
}
