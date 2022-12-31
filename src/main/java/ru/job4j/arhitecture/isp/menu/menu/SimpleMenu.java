package ru.job4j.arhitecture.isp.menu.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final SimpleMenuItem rootMenuItem;

    public SimpleMenu() {
        this.rootMenuItem = new SimpleMenuItem(Menu.ROOT, null);
    }

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean isAdded;
        if (Menu.ROOT.equals(parentName)) {
            rootMenuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            isAdded = true;
        } else {
            Optional<ItemInfo> rootInfo = findItem(parentName);
            if (rootInfo.isEmpty()) {
                isAdded = false;
            } else {
                rootInfo.get()
                        .menuItem
                        .getChildren()
                        .add(new SimpleMenuItem(childName, actionDelegate));
                isAdded = true;
            }
        }
        return isAdded;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> select;
        if (itemName == null) {
            select = Optional.empty();
        }
        if (Menu.ROOT.equals(itemName)) {
            select = Optional.of(new MenuItemInfo(rootMenuItem, ""));
        } else {
            select = findItem(itemName).map(itemInfo -> new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
        }
        return select;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            private final DFSIterator dfsIterator = new DFSIterator();
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo next = dfsIterator.next();
                return new MenuItemInfo(next.menuItem, next.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        if (name == null) {
            return Optional.empty();
        }

        ItemInfo res = null;
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo next = iterator.next();
            if (name.equals(next.menuItem.getName())) {
                res = next;
                break;
            }
        }
        return Optional.ofNullable(res);
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootMenuItem.getChildren()) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
