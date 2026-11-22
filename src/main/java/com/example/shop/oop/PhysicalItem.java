package com.example.shop.oop;

public class PhysicalItem extends Item {
    private final double weightKg;

    public PhysicalItem(String name, int price, double weightKg) {
        super(name, price);
        this.weightKg = weightKg;
    }

    @Override
    public String describe() {
        return getName() + " (физический товар, " + weightKg + " кг)";
    }

}