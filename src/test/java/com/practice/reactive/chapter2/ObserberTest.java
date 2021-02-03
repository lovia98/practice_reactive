package com.practice.reactive.chapter2;

import com.practice.reactive.chapter2.impl.ConcreateObserverA;
import com.practice.reactive.chapter2.impl.ConcreateObserverB;
import com.practice.reactive.chapter2.impl.ConcreateSubject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ObserberTest {

    /**
     * 구독자 타입을 원하는대로 교체하여 주입
     */
    @Test
    void observersHandleEventsFromSubject() {
        //given
        Subject<String> subject = new ConcreateSubject();
        Observer<String> observerA = Mockito.spy(new ConcreateObserverA());
        Observer<String> observerB = Mockito.spy(new ConcreateObserverB());

        //when
        subject.notifyObservers("No listeners");

        subject.registerObserver(observerA);
        subject.notifyObservers("Message for A");

        subject.registerObserver(observerB);
        subject.notifyObservers("Message for A & B");

        subject.unregisterObserver(observerA);
        subject.notifyObservers("Message for B");

        subject.unregisterObserver(observerB);
        subject.notifyObservers("No listeners");

        Mockito.verify(observerA, Mockito.times(1)).observe("Message for A");
        Mockito.verify(observerA, Mockito.times(1)).observe("Message for A & B");
        Mockito.verifyNoMoreInteractions(observerA);

        Mockito.verify(observerB, Mockito.times(1)).observe("Message for A & B");
        Mockito.verify(observerB, Mockito.times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(observerB);
    }

    @Test
    public void subjectLeveragesLamdas() {
        Subject<String> subject = new ConcreateSubject();
        subject.registerObserver(e -> System.out.println("A: " + e));
        subject.registerObserver(e -> System.out.println("B: " + e));
        subject.notifyObservers("This message will receive A & B");
    }


}
