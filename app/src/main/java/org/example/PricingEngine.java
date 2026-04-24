package org.example;

import java.util.List;

public class PricingEngine {

    private final DiscountService discountService = new DiscountService();

    public double calculate(List<Double> prices,
            List<Integer> quantities,
            String customerType,
            String discountCode) {

        double subtotal = 0;

        for (int i = 0; i < prices.size(); i++) {
            subtotal += prices.get(i) * quantities.get(i);
        }

        double discount = discountService.calculateDiscount(subtotal, customerType, discountCode);

        double taxed = (subtotal - discount) * 0.19;

        return subtotal - discount + taxed;
    }
}