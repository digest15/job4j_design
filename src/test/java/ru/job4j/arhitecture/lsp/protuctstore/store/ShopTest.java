package ru.job4j.arhitecture.lsp.protuctstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.MockFood;


import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    @Test
    public void whenAddStoredLess25Expiration() {
        Store<MockFood> store = new Shop<>((e) -> 0);
        MockFood someExpiring = new MockFood(13.0, 0.0);
        boolean isAdded = store.add(someExpiring);
        assertThat(isAdded).isFalse();
    }

    @Test
    public void whenAddStoredMoreOrEqual25ButLess75Expiration() {
        Store<MockFood> store = new Shop<>((e) -> 0);

        MockFood someExpiring1 = new MockFood(25.0, 0.0);
        boolean isAdded1 = store.add(someExpiring1);
        assertThat(isAdded1).isTrue();

        MockFood someExpiring2 = new MockFood(74.0, 0.0);
        boolean isAdded2 = store.add(someExpiring2);
        assertThat(isAdded2).isTrue();
    }

    @Test
    public void whenAddStoredMore75Expiration() {
        Store<MockFood> store = new Shop<>((e) -> 0);
        MockFood someExpiring = new MockFood(75.0, 0.0);
        boolean isAdded = store.add(someExpiring);
        assertThat(isAdded).isFalse();
    }

}