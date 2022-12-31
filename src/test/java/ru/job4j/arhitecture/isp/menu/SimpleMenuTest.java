package ru.job4j.arhitecture.isp.menu;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.isp.menu.menu.ActionDelegate;
import ru.job4j.arhitecture.isp.menu.menu.Menu;
import ru.job4j.arhitecture.isp.menu.menu.SimpleMenu;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo(
                "Сходить в магазин",
                List.of("Купить продукты"),
                STUB_ACTION,
                "1.")
        ).isEqualTo(
                menu.select("Сходить в магазин").get()
        );
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"),
                STUB_ACTION,
                "1.1."
        )).isEqualTo(
                menu.select("Купить продукты").get()
        );
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку",
                List.of(),
                STUB_ACTION,
                "2.")
        ).isEqualTo(
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectRoot() {
        Menu menu = new SimpleMenu();

        Menu.MenuItemInfo expected = new Menu.MenuItemInfo(Menu.ROOT, List.of(), null, "");
        assertThat(menu.select(Menu.ROOT).get()).isEqualTo(expected);

        String menuName = "Сходить в магазин";
        menu.add(Menu.ROOT, menuName, STUB_ACTION);
        Menu.MenuItemInfo expected1 = new Menu.MenuItemInfo(Menu.ROOT, List.of(menuName), null, "");
        assertThat(menu.select(Menu.ROOT).get()).isEqualTo(expected1);
    }

    @Test
    public void whenSelectNull() {
        Menu menu = new SimpleMenu();
        String menuName = "Сходить в магазин";
        menu.add(Menu.ROOT, menuName, STUB_ACTION);
        assertThat(menu.select(null)).isEqualTo(Optional.empty());
    }

    @Test
    public void whenSelectMenuWhichIsNot() {
        Menu menu = new SimpleMenu();
        String menuName1 = "Сходить в магазин";
        String menuName2 = "Купить корм";

        assertThat(menu.select(menuName1)).isEqualTo(Optional.empty());

        menu.add(Menu.ROOT, menuName1, STUB_ACTION);
        assertThat(menu.select(menuName2)).isEqualTo(Optional.empty());
    }
}