package ru.job4j.arhitecture.ocp.example3;

/**
 * Example 3
 * Этот класс нарушает принцип закрытости/расширяемости
 * Потому что, если нужно будет создать новый тип логгера, то этот класс тоже нужно будет изменить
 */
public class Mailer {
    private Logger logger;

    public Mailer(Logger logger) {
        this.logger = logger;
    }

    public void sendMessage(String message) {
        logger.log(String.format("Отправлено %s'", message));
    }
}
