package ru.job4j.arhitecture.isp.menu;

import java.util.function.Consumer;

public class MenuPrinterImpl implements MenuPrinter {

    private final String splitter;
    private final Consumer<String> consumer;

    public MenuPrinterImpl(String splitter, Consumer<String> consumer) {
        this.consumer = consumer;
        this.splitter = splitter;
    }

    @Override
    public void print(Menu menu) {
        String tab = "";
        menu.select(Menu.ROOT).ifPresent(
                item -> printItem(menu, item, tab)
        );
    }

    private void printItem(Menu menu, Menu.MenuItemInfo rootItem, String tab) {
        boolean isRoot = !Menu.ROOT.equals(rootItem.getName());
        if (isRoot) {
            consumer.accept(String.format("%s%s %s", tab, rootItem.getNumber(), rootItem.getName()));
        }
        String tab1 = isRoot ? tab.concat(splitter) : tab;
        rootItem.getChildren().forEach(
                name -> menu.select(name)
                        .ifPresent(
                                item -> printItem(menu, item, tab1)
                        )
        );
    }
}
