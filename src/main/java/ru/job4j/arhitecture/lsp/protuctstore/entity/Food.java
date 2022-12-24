package ru.job4j.arhitecture.lsp.protuctstore.entity;

import ru.job4j.arhitecture.lsp.protuctstore.expiration.ExpirationCalculator;

import java.time.LocalDate;

public abstract class Food implements Expiring, Pricing {
    protected String name;
    protected LocalDate createDate;
    protected LocalDate expiryDate;

    protected Double price;

    protected Double discount;

    protected ExpirationCalculator<LocalDate> expirationCalculator;

    public Food(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
