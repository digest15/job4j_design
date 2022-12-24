package ru.job4j.arhitecture.lsp.protuctstore.entity;

import java.time.LocalDate;

public class MockFood implements Expiring, Pricing {
    private final double expiration;
    private final Double price;

    public MockFood(double expiration, Double price) {
        this.expiration = expiration;
        this.price = price;
    }

    @Override
    public double calculateExpiration(LocalDate forDate) {
        return expiration;
    }

    @Override
    public void setPrice(Double price) {

    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setDiscount(Double discount) {

    }

    @Override
    public Double getDiscount() {
        return null;
    }
}
