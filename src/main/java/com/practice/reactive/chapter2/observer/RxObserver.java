package com.practice.reactive.chapter2.observer;

/**
 * Rxjava
 */
public interface RxObserver<T> {

    //이벤트 시작과 끝을 알려 줄수 잇는 기능 추가
    void onNext(T next);
    void onComplete();

    //오류가 발생했음을 알려주는 기능 추가
    void onError();
}
