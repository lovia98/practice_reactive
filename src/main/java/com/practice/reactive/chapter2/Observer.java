package com.practice.reactive.chapter2;

public interface Observer<T> {
    void observe(T event);
}
