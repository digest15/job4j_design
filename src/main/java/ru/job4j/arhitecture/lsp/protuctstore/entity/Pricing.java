package ru.job4j.arhitecture.lsp.protuctstore.entity;

public interface Pricing {
    void setPrice(Double price);

    Double getPrice();

    void setDiscount(Double discount);

    Double getDiscount();
}
