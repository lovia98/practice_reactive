package com.practice.reactive.chapter2.observer.impl;

import com.practice.reactive.chapter2.observer.Observer;

public class ConcreateObserverB implements Observer {

    @Override
    public void observe(Object event) {
        System.out.println("Observer B: " + event);
    }
}
