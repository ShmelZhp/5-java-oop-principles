package com.example.task02;

public class DiscountBill extends Bill{
    private double discountPercentage;

    public DiscountBill(double discountPercentage){
        setDiscountPercentage(discountPercentage);
    }

    public void setDiscountPercentage(double discountPercentage){
        if (discountPercentage < 0 || discountPercentage > 100){
            throw new IllegalArgumentException("Скидка должна быть в диапазона от 1 до 100");
        }
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage(){
        return discountPercentage;
    }

    public long getDiscountAbsolute(){
        long originalPrice = super.getPrice();
        return Math.round(originalPrice * discountPercentage / 100);
    }

    public long getPrice(){
        long originalPrice = super.getPrice();
        long discount = getDiscountAbsolute();
        return originalPrice - discount;
    }

}
