package com.practice.reactive.chapter2.observer.impl;

import com.practice.reactive.chapter2.observer.Observer;

public class ConcreateObserverA implements Observer<String> {

    @Override
    public void observe(String event) {
        System.out.println("Observer A: " + event);
    }
}
