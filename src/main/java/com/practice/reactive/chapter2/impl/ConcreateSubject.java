package com.practice.reactive.chapter2.impl;

import com.practice.reactive.chapter2.Observer;
import com.practice.reactive.chapter2.Subject;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcreateSubject implements Subject<String> {

    //CopyOnWriteArraySet - 업데이트 작업이 발생할때 마다 새 복사본을 생성하는 collection
    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();

    /**
     * 구독자 추가
     * @param observer
     */
    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    /**
     * 구독자 삭제
     * @param observer
     */
    @Override
    public void unregisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    /**
     * 구독자들에게 이벤트 발행하기
     *
     * @param event
     */
    @Override
    public void notifyObservers(String event) {
        observers.forEach(observer -> observer.observe(event));
    }
}
