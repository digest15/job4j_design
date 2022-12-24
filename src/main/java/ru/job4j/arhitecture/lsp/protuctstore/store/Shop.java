package ru.job4j.arhitecture.lsp.protuctstore.store;

import ru.job4j.arhitecture.lsp.protuctstore.discount.Discounter;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Expiring;
import ru.job4j.arhitecture.lsp.protuctstore.entity.Pricing;

import java.time.LocalDate;

public class Shop<E extends Expiring & Pricing> extends AbstractStore<E> {

    private final Discounter<E> discounter;

    public Shop(Discounter<E> discounter) {
        this.discounter = discounter;
    }

    @Override
    protected boolean isStored(E value) {
        double expiration = value.calculateExpiration(LocalDate.now());
        return expiration >= 25.0 && expiration < 75.0;
    }

    @Override
    public boolean add(E value) {
        boolean res = super.add(value);
        if (res) {
            double discount = discounter.calculate(value);
            value.setDiscount(discount);
            value.setPrice(value.getPrice() - discount);
        }
        return res;
    }
}
