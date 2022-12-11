package ru.job4j.arhitecture.ocp.example1and2;

import ru.job4j.arhitecture.ocp.example1and2.entity.Circle;
import ru.job4j.arhitecture.ocp.example1and2.entity.Shape;
import ru.job4j.arhitecture.ocp.example1and2.entity.Square;

import java.util.ArrayList;
import java.util.List;

/**
 * Example 1
 * Этот класс нарушает принцип закрытости/расширения
 * так при добавлении нового типа фигуры придется изменять метод этого класса
 */
public class AreaCalculator {
    private List<Shape> shapes;

    public AreaCalculator() {
        shapes = new ArrayList<>();
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public double calculateArea() {
        double area = 0;
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                area += caclulateForCircle(shape);
            } else if (shape instanceof Square) {
                area += caclulateForSquare(shape);
            }
        }
        return area;
    }

    private double caclulateForSquare(Shape shape) {
        Square square = (Square) shape;
        return Math.sqrt(square.side);
    }

    private double caclulateForCircle(Shape shape) {
        Circle circle = (Circle) shape;
        return Math.PI * Math.sqrt(circle.radius);
    }
}
