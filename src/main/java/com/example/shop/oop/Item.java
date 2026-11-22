package com.example.shop.oop;

public abstract class Item {
    private final String name;
    private final int price; // цена в рублях

    protected Item(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // Каждый наследник обязан описать себя по-своему
    public abstract String describe();
}