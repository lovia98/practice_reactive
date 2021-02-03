package com.practice.reactive.chapter2.pubsub;

/**
 * 온도 정보
 */
public  final class Temperature {

    private final double value;

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
