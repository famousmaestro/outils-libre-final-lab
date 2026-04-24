package org.example;

import java.util.List;

public class PricingEngine {

    private final DiscountService discountService = new DiscountService();
    private final TaxService taxService = new TaxService();

    public PricingResult calculate(List<Double> prices,
            List<Integer> quantities,
            String customerType,
            String discountCode) {

        double subtotal = calculateSubtotal(prices, quantities);

        double discount = discountService.calculateDiscount(
                subtotal,
                customerType,
                discountCode);

        double afterDiscount = subtotal - discount;

        double tax = taxService.calculateTax(afterDiscount);

        return new PricingResult(subtotal, discount, tax, afterDiscount + tax);
    }

    private double calculateSubtotal(List<Double> prices, List<Integer> quantities) {
        double subtotal = 0;

        for (int i = 0; i < prices.size(); i++) {
            subtotal += prices.get(i) * quantities.get(i);
        }

        return subtotal;
    }
}
