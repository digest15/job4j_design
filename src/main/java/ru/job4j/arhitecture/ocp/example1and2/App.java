package ru.job4j.arhitecture.ocp.example1and2;

import ru.job4j.arhitecture.ocp.example1and2.entity.Circle;
import ru.job4j.arhitecture.ocp.example1and2.entity.Square;

public class App {
    public static void main(String[] args) {
        Circle circle = new Circle(3);
        Square square = new Square(4);
        AreaCalculator calculator = new AreaCalculator();
        calculator.add(square);
        calculator.add(circle);
        System.out.println(calculator.calculateArea());
    }
}
