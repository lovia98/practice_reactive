package com.practice.reactive.chapter1.model;

import lombok.Getter;

@Getter
public class Input {

    private int amount;
    private int price;

    public Input(int amount, int price) {
        this.amount = amount;
        this.price = price;
    }
}
