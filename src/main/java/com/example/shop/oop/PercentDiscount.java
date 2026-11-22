package com.example.shop.oop;

public class PercentDiscount implements DiscountPolicy {
    private final int percent;

    public PercentDiscount(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }
        this.percent = percent;
    }

    @Override
    public int apply(int price) {
        return price * (100 - percent) / 100;
    }
}