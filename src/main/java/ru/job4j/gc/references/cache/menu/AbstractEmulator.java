package ru.job4j.gc.references.cache.menu;

import ru.job4j.gc.references.cache.AbstractCache;

public abstract class AbstractEmulator {

    protected AbstractCache<?, ?> cache;


    public void start() {
        init();
        menu();
    }

    protected abstract void menu();

    protected abstract AbstractCache<?, ?> createCache();

    protected void init() {
        cache = createCache();
    }
}
