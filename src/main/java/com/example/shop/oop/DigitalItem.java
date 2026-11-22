package com.example.shop.oop;

public class DigitalItem extends Item {
    private final int sizeMb;

    public DigitalItem(String name, int price, int sizeMb) {
        super(name, price);
        this.sizeMb = sizeMb;
    }

    @Override
    public String describe() {
        return getName() + " (цифровой товар, " + sizeMb + " МБ)";
    }
}

