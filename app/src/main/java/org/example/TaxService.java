package org.example;

public class TaxService {

    private static final double TAX_RATE = 0.19;

    public double calculateTax(double amount) {
        return amount * TAX_RATE;
    }
}
