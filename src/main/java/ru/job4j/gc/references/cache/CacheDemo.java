package ru.job4j.gc.references.cache;

import ru.job4j.gc.references.cache.menu.AbstractEmulator;
import ru.job4j.gc.references.cache.menu.DirCacheEmulator;

public class CacheDemo {
    public static void main(String[] args) {
        AbstractEmulator emulator = new DirCacheEmulator();
        emulator.start();
    }
}
