package ru.job4j.arhitecture.lsp.protuctstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Stored;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    @Test
    public void whenAddStoredLess25Expiration() {
        Store<Stored> store = new Shop();
        Stored someStored = () -> 13;
        boolean isAdded = store.add(someStored);
        assertThat(isAdded).isFalse();
    }

    @Test
    public void whenAddStoredMoreOrEqual25ButLess75Expiration() {
        Store<Stored> store = new Shop();

        Stored someStored1 = () -> 25;
        boolean isAdded1 = store.add(someStored1);
        assertThat(isAdded1).isTrue();

        Stored someStored2 = () -> 74;
        boolean isAdded2 = store.add(someStored2);
        assertThat(isAdded2).isTrue();
    }

    @Test
    public void whenAddStoredMore75Expiration() {
        Store<Stored> store = new Shop();
        Stored someStored = () -> 75;
        boolean isAdded = store.add(someStored);
        assertThat(isAdded).isFalse();
    }

}