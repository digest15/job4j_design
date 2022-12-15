package ru.job4j.arhitecture.lsp.protuctstore;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Food;
import ru.job4j.arhitecture.lsp.protuctstore.store.Store;
import ru.job4j.arhitecture.lsp.protuctstore.store.Trash;
import ru.job4j.arhitecture.lsp.protuctstore.store.Warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


class FoodControlQualityTest {
    @Test
    public void whenRedistributionStores() {
        Store<Food> warehouse = new Warehouse();
        Store<Food> trash = new Trash();
        List<Store<Food>> stores = List.of(warehouse, trash);
        ControlQuality<Food> controlQuality = new FoodControlQuality();
        Food someFood = new Food() {
            @Override
            public double culculateExpiration() {
                return 80;
            }
        };
        controlQuality.add(someFood);
        int count = controlQuality.redistribution(stores);
        assertThat(count).isEqualTo(1);

        assertThat(warehouse.size()).isEqualTo(0);
        assertThat(trash.size()).isEqualTo(1);
    }
}