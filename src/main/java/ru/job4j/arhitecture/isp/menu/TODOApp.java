package ru.job4j.arhitecture.isp.menu;


import ru.job4j.arhitecture.isp.menu.menu.ActionDelegate;
import ru.job4j.arhitecture.isp.menu.menu.Menu;
import ru.job4j.arhitecture.isp.menu.menu.SimpleMenu;
import ru.job4j.arhitecture.isp.menu.menuprinter.MenuPrinter;
import ru.job4j.arhitecture.isp.menu.menuprinter.MenuPrinterImpl;

import java.io.PrintStream;
import java.util.*;

public class TODOApp {

    private static final ActionDelegate STUB_ACTION = System.out::println;

    private final Set<String> tasks = new HashSet<>();

    private final Scanner scanner;

    private final PrintStream printStream;

    private final Menu menu;

    private boolean run = true;

    public static void main(String[] args) {
        TODOApp todoApp = new TODOApp(new Scanner(System.in), System.out, new SimpleMenu());
        MenuPrinter menuPrinter = new MenuPrinterImpl("    ", System.out::println);
        todoApp.start(menuPrinter);
    }

    public TODOApp(Scanner scanner, PrintStream printStream, Menu menu) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.menu = initMenu(menu);
    }

    public void start(MenuPrinter menuPrinter) {
        while (run) {
            menuPrinter.print(menu);
            String userChoise = scanner.nextLine();
            menu.select(userChoise).ifPresent(
                            i -> i.getActionDelegate().delegate()
            );
        }
    }

    private Menu initMenu(Menu menu) {
        menu.add(Menu.ROOT, "Создать задачу", this::addTask);
        menu.add(Menu.ROOT, "Удалить задачу", this::removeTask);
        menu.add(Menu.ROOT, "Показать все задачи", this::listTasks);
        menu.add(Menu.ROOT, "Закрыть приложение", () -> run = false);
        menu.add(Menu.ROOT, "Добавить пункт меню", this::addMenu);
        menu.add(Menu.ROOT, "Добавить пункт меню в корень", this::addRootMenu);
        return menu;
    }

    private void addTask() {
        printStream.println("Создание задачи. Введите название:");
        String title = scanner.nextLine();
        tasks.add(title);
    }

    private void removeTask() {
        printStream.println("Удаление задачи. Введите название");
        String title = scanner.nextLine();
        tasks.remove(title);
    }

    private void listTasks() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        tasks.forEach(sj::add);
        printStream.println(sj.toString());
    }

    private void addRootMenu() {
        addMenuItem(Menu.ROOT);
    }

    private void addMenu() {
        printStream.println("Выберите родительский пункт:");
        String root = scanner.nextLine();
        menu.select(root).ifPresentOrElse(
                item -> addMenuItem(item.getName()),
                () -> printStream.println("Такой пункт меню не найден!")
        );
    }

    private void addMenuItem(String parent) {
        printStream.println("Введите название пункта меню:");
        String name = scanner.nextLine();
        boolean isAdded = menu.add(parent, name, STUB_ACTION);
        if (isAdded) {
            printStream.println("Элемент успешно добавлен!");
        }
    }
}
