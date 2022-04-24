package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = putValue(hash(key), key, value);
        if (rsl) {
            modCount++;
            count++;
        }
        if (((float) count / capacity) > LOAD_FACTOR) {
            expand();
        }
        return rsl;
    }

    private int hash(K key) {
        int hash = 0;
        if (key != null) {
            int hashCode = key.hashCode();
            hash = hashCode ^ (hashCode >>> 16);
        }
        return hash;
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            if (table[i] != null) {
                put(table[i].key, table[i].value);
            }
        }
    }

    private boolean putValue(int hash, K key, V value) {
        int i = indexFor(hash);
        boolean isChange = (table[i] == null);
        if (isChange) {
            table[i] = new MapEntry<>(key, value);
        }
        return isChange;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = getEntry(hash(key), key);
        return entry == null ? null : entry.value;
    }

    private MapEntry<K, V> getEntry(int hash, K key) {
        MapEntry<K, V> entry = table[indexFor(hash)];
        return (entry == null || (entry.key != key && !(hash(entry.key) == hash && entry.key.equals(key)))) ? null : entry;
    }

    @Override
    public boolean remove(K key) {
        MapEntry<K, V> entry = getEntry(hash(key), key);
        if (entry != null) {
            int i = indexFor(hash(entry.key));
            table[i] = null;
            entry.key = null;
            entry.value = null;

            modCount++;
            count--;
        }
        return entry != null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nextIndex() > 0;
            }

            private int nextIndex() {
                int i;
                for (i = cursor; i < capacity; i++) {
                    if (table[i] != null) {
                        break;
                    }
                }
                return i == capacity ? -1 : i;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                cursor = nextIndex() + 1;
                return table[cursor - 1].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
