package ru.job4j.arhitecture.isp.menu.menu;


import java.util.List;

public interface MenuItem {
    String getName();

    List<MenuItem> getChildren();

    ActionDelegate getActionDelegate();
}
