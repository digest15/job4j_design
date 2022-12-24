package ru.job4j.arhitecture.lsp.protuctstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.MockFood;


import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {
    @Test
    public void whenAddStoredLess75Expiration() {
        Store<MockFood> store = new Trash<>();
        MockFood someExpiring = new MockFood(74.0, 0.0);
        boolean isAdded = store.add(someExpiring);
        assertThat(isAdded).isFalse();
    }

    @Test
    public void whenAddStoredMoreOrEqual75Expiration() {
        Store<MockFood> store = new Trash<>();
        MockFood someExpiring = new MockFood(75.0, 0.0);
        boolean isAdded = store.add(someExpiring);
        assertThat(isAdded).isTrue();
    }
}