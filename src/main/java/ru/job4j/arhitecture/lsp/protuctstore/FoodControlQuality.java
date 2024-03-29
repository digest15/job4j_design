package ru.job4j.arhitecture.lsp.protuctstore;

import ru.job4j.arhitecture.lsp.protuctstore.entity.Food;
import ru.job4j.arhitecture.lsp.protuctstore.store.Store;

import java.util.ArrayList;
import java.util.List;

public class FoodControlQuality implements ControlQuality<Food> {
    private List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public int redistribution(List<Store<Food>> stores) {
        int countModifications = 0;
        for (Food food : foods) {
            for (Store<Food> store : stores) {
                if (store.add(food)) {
                    countModifications++;
                }
            }
        }
        return countModifications;
    }

    @Override
    public int reSort(List<Store<Food>> stores) {
        foods = new ArrayList<>();
        for (Store<Food> store : stores) {
            foods.addAll(store.clear());
        }
        return redistribution(stores);
    }
}
