package ru.job4j.arhitecture.lsp.protuctstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.MockFood;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {
    @Test
    public void whenAddStoredLess25Expiration() {
        Store<MockFood> store = new Warehouse<>();
        MockFood someExpiring = new MockFood(13.0, 0.0);
        boolean isAdded = store.add(someExpiring);
        assertThat(isAdded).isTrue();
    }

    @Test
    public void whenAddStoredMore25Expiration() {
        Store<MockFood> store = new Warehouse<>();
        MockFood someExpiring = new MockFood(50.0, 0.0);
        boolean isAdded = store.add(someExpiring);
        assertThat(isAdded).isFalse();
    }
}