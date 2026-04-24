package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class PricingEngineTest {

    @Test
    public void shouldCalculateBasicPrice() {

        PricingEngine engine = new PricingEngine();

        double result = engine.calculate(
                List.of(100.0),
                List.of(1),
                "REGULAR",
                "");

        assertTrue(result > 0);
    }

    @Test
    public void save10ShouldReducePrice() {

        PricingEngine engine = new PricingEngine();

        double result = engine.calculate(
                List.of(100.0),
                List.of(1),
                "REGULAR",
                "SAVE10");

        assertTrue(result < 119.0);
    }

    @Test
    public void vipShouldGetExtraDiscount() {

        PricingEngine engine = new PricingEngine();

        double result = engine.calculate(
                List.of(100.0),
                List.of(1),
                "VIP",
                "");

        assertTrue(result < 119.0);
    }
}
