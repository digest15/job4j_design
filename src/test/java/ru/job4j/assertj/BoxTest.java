package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        String type = "Sphere";
        int vertex = 0;
        int edge = 10;
        Box box = new Box(vertex, edge);
        String name = box.whatsThis();
        assertThat(name).isEqualTo(type);
        assertThat(box.getNumberOfVertices()).isEqualTo(vertex);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisTetrahedron() {
        String type = "Tetrahedron";
        int vertex = 0;
        int edge = 10;
        Box box = new Box(vertex, edge);
        String name = box.whatsThis();
        assertThat(name).isEqualTo(type);
        assertThat(box.getNumberOfVertices()).isEqualTo(vertex);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisCube() {
        String type = "Cube";
        int vertex = 0;
        int edge = 10;
        Box box = new Box(vertex, edge);
        String name = box.whatsThis();
        assertThat(name).isEqualTo(type);
        assertThat(box.getNumberOfVertices()).isEqualTo(vertex);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisUnknown() {
        String type = "Unknown object";
        int vertex = 7;
        int edge = 10;
        Box box = new Box(vertex, edge);
        String name = box.whatsThis();
        assertThat(name).isEqualTo(type);
        assertThat(box.getNumberOfVertices()).isNegative();
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenSphereAreaEquals() {
        double expectedArea = 1256.637;
        int vertex = 0;
        int edge = 10;
        Box box = new Box(vertex, edge);
        double area = box.getArea();
        assertThat(area).isCloseTo(expectedArea, withPrecision(0.001d));
    }

    @Test
    void whenTetrahedronAreaEquals() {
        double expectedArea = 173.205;
        int vertex = 4;
        int edge = 10;
        Box box = new Box(vertex, edge);
        double area = box.getArea();
        assertThat(area).isCloseTo(expectedArea, withPrecision(0.001d));
    }

    @Test
    void whenCubeAreaEquals() {
        double expectedArea = 600.00;
        int vertex = 8;
        int edge = 10;
        Box box = new Box(vertex, edge);
        double area = box.getArea();
        assertThat(area).isCloseTo(expectedArea, withPrecision(0.001d));
    }

    @Test
    void whenUnknownAreaEquals() {
        double expectedArea = 0;
        int vertex = 10;
        int edge = 10;
        Box box = new Box(vertex, edge);
        double area = box.getArea();
        assertThat(area).isEqualTo(expectedArea);
    }
}