package ru.job4j.arhitecture.ocp.example1and2.entity;

/**
 * Example 2
 * Этот класс нарушает принцип закрытости/расширения
 * Так как при изменении внутреннего представления этого класса
 * придется изменять использующие его классы
 */
public class Square extends Shape {
    public double side;

    public Square(double side) {
        this.side = side;
    }
}
