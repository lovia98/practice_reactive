package com.practice.reactive.chapter1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Output {

    private int result;

    public Output(int amout, int price) {
        this.result = amout * price;
    }
}
