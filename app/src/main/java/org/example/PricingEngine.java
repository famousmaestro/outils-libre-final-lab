package org.example;

import java.util.List;

public class PricingEngine {

    public double calculate(List<Double> prices,
            List<Integer> quantities,
            String customerType,
            String discountCode) {

        double subtotal = 0;

        for (int i = 0; i < prices.size(); i++) {
            subtotal += prices.get(i) * quantities.get(i);
        }

        double discount = 0;

        if ("SAVE10".equals(discountCode)) {
            discount = subtotal * 0.10;
        } else if ("SAVE20".equals(discountCode)) {
            discount = subtotal * 0.20;
        }

        if ("VIP".equals(customerType)) {
            discount += subtotal * 0.05;
        }

        double taxed = (subtotal - discount) * 0.19;

        return subtotal - discount + taxed;
    }
}