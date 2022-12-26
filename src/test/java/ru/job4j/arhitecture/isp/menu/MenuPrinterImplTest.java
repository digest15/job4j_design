package ru.job4j.arhitecture.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class MenuPrinterImplTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrintMenu() {
        String expected = """
                1. Сходить в магазин
                --1.1. Купить продукты
                ----1.1.1. Купить хлеб
                ----1.1.2. Купить молоко
                2. Покормить собаку""";

        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        StringJoiner sj = new StringJoiner("\n");
        MenuPrinter printer = new MenuPrinterImpl("--", sj::add);
        printer.print(menu);

        assertThat(sj.toString()).isEqualTo(expected);
    }

    @Test
    public void whenPrintEmptyMenu() {
        String expected = "";
        Menu menu = new SimpleMenu();
        StringJoiner sj = new StringJoiner("\n");
        MenuPrinter printer = new MenuPrinterImpl("--", sj::add);
        printer.print(menu);
        assertThat(sj.toString()).isEqualTo(expected);
    }
}