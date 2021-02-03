package com.practice.reactive.chapter2.impl;

import com.practice.reactive.chapter2.Observer;

public class ConcreateObserverB implements Observer {

    @Override
    public void observe(Object event) {
        System.out.println("Observer B: " + event);
    }
}
