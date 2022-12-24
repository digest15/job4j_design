package ru.job4j.arhitecture.lsp.protuctstore.discount;


import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.lsp.protuctstore.entity.MockFood;

import static org.assertj.core.api.Assertions.*;

class ExpirationPercentDiscounterTest {
    @Test
    public void whenCalculate() {
        double price = 100.0;
        double expiration = 50.0;
        double expected = 50.0;
        MockFood mockFood = new MockFood(expiration, price);
        ExpirationPercentDiscounter<MockFood> discounter = new ExpirationPercentDiscounter<>();
        assertThat(discounter.calculate(mockFood)).isEqualTo(expected);
    }
}