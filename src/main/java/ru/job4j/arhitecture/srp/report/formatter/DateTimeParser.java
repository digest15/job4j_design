package ru.job4j.arhitecture.srp.report.formatter;

public interface DateTimeParser<T> {
    String parse(T t);
}
