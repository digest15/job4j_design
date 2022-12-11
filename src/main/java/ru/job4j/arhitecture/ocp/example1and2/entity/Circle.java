package ru.job4j.arhitecture.ocp.example1and2.entity;

/**
 * Example 2
 * Этот класс нарушает принцип закрытости/расширения
 * Так как при изменении внутреннего представления этого класса
 * придется изменять использующие его классы
 */
public class Circle extends Shape {
    public double radius;

    public Circle(long redius) {
        this.radius = redius;
    }
}
