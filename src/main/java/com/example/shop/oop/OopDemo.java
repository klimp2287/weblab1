package com.example.shop.oop;

import java.util.List;

public class OopDemo {
    public static void main(String[] args) {
        List<Item> items = List.of(
                new PhysicalItem("Клавиатура", 3500, 0.8),
                new DigitalItem("Антивирус", 1200, 250),
                new PhysicalItem("Монитор", 15000, 4.5)
        );

        DiscountPolicy pDiscount = new PercentDiscount(10);

        for (Item item : items) {
            int finalPrice = pDiscount.apply(item.getPrice());
            System.out.println(item.describe() + ": "
                    + item.getPrice() + " -> " + finalPrice + " руб.");
        }

        DiscountPolicy fDiscount = new FixedDiscount(500);

        for (Item item : items) {
            int finalPrice = fDiscount.apply(item.getPrice());
            System.out.println(item.describe() + ": "
                    + item.getPrice() + " -> " + finalPrice + " руб.");
        }
    }
}