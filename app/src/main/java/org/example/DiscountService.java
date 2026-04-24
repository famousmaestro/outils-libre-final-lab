package org.example;

public class DiscountService {

    public double calculateDiscount(double subtotal,
            String customerType,
            String discountCode) {

        double discount = 0;

        // Promo codes
        if ("SAVE10".equals(discountCode)) {
            discount = subtotal * 0.10;
        } else if ("SAVE20".equals(discountCode)) {
            discount = subtotal * 0.20;
        }

        // Customer type discount
        if ("VIP".equals(customerType)) {
            discount += subtotal * 0.05;
        }

        return discount;
    }
}
