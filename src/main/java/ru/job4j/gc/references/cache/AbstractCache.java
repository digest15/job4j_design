package ru.job4j.gc.references.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        return Optional.ofNullable(cache.get(key))
                .map(SoftReference::get)
                .orElseGet(() -> {
                    V value = load(key);
                    put(key, value);
                    return value;
                });
    }

    protected abstract V load(K key);
}
