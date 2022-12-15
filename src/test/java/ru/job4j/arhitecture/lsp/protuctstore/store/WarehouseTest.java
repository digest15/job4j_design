package ru.job4j.arhitecture.lsp.protuctstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Stored;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {
    @Test
    public void whenAddStoredLess25Expiration() {
        Store<Stored> store = new Warehouse();
        Stored someStored = () -> 13;
        boolean isAdded = store.add(someStored);
        assertThat(isAdded).isTrue();
    }

    @Test
    public void whenAddStoredMore25Expiration() {
        Store<Stored> store = new Warehouse();
        Stored someStored = () -> 50;
        boolean isAdded = store.add(someStored);
        assertThat(isAdded).isFalse();
    }
}