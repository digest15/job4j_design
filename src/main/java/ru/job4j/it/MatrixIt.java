package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = true;

        int r = row;
        while (r < data.length && data[r].length == 0) {
            r++;
            if (r == data.length) {
                res = false;
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (column == data[row].length) {
            column = 0;
            row++;
        }
        return data[row][column++];
    }
}
