package ru.job4j.arhitecture.lsp.protuctstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Stored;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {
    @Test
    public void whenAddStoredLess75Expiration() {
        Store<Stored> store = new Trash();
        Stored someStored = () -> 74;
        boolean isAdded = store.add(someStored);
        assertThat(isAdded).isFalse();
    }

    @Test
    public void whenAddStoredMoreOrEqual75Expiration() {
        Store<Stored> store = new Trash();
        Stored someStored = () -> 75;
        boolean isAdded = store.add(someStored);
        assertThat(isAdded).isTrue();
    }
}