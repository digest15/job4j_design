package ru.job4j.arhitecture.srp.example2;

/**
 * Example 2
 * Этот класс нарушает принцип Single Responsibility Principle
 * Он знает какой класс калькулятора создавать.
 * знает как произвести расчет
 * и как вывести результаты расчета
 */
public class Culculation {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int res = calculator.plus(1, 2);
        res = calculator.minus(res, 3);
        System.out.println(res);
    }
}
