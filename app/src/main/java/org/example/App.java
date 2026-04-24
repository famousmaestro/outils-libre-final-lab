package org.example;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Double> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        String customerType = "REGULAR";
        String discountCode = "NONE";

        if (args.length >= 4) {
            String[] pricesArr = args[0].split(",");
            for (String p : pricesArr) {
                prices.add(Double.parseDouble(p.trim()));
            }

            String[] qtyArr = args[1].split(",");
            for (String q : qtyArr) {
                quantities.add(Integer.parseInt(q.trim()));
            }

            customerType = args[2];
            discountCode = args[3];
        } else {
            // Default for non-arg runs
            prices = List.of(100.0, 50.0);
            quantities = List.of(1, 2);
            customerType = "VIP";
            discountCode = "SAVE10";
        }

        PricingEngine engine = new PricingEngine();
        PricingResult result = engine.calculate(prices, quantities, customerType, discountCode);

        System.out.println("Subtotal: " + result.getSubtotal());
        System.out.println("Discount amount: " + result.getDiscountAmount());
        System.out.println("Tax: " + result.getTax());
        System.out.println("Final price: " + result.getFinalPrice());
    }
}
