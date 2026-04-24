package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {

        PricingEngine engine = new PricingEngine();

        double result = engine.calculate(
                List.of(100.0, 50.0),
                List.of(1, 2),
                "VIP",
                "SAVE10");

        System.out.println("Final Price: " + result);
    }
}
