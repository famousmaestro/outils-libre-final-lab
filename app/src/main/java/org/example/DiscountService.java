package org.example;

public class DiscountService {

    public double calculateDiscount(double subtotal, String customerType, String discountCode) {

        double discount = 0;

        if ("SAVE10".equals(discountCode)) {
            discount = subtotal * 0.10;
        } else if ("SAVE20".equals(discountCode)) {
            discount = subtotal * 0.20;
        }

        if ("VIP".equals(customerType)) {
            discount += subtotal * 0.05;
        }

        return discount;
    }
}