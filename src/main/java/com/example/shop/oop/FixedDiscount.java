package com.example.shop.oop;

public class FixedDiscount implements DiscountPolicy {
    private final int rub;

    public FixedDiscount(int rub) {
        if (rub < 0) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }
        this.rub = rub;
    }

    @Override
    public int apply(int price) {
        return price - rub;
    }
}
