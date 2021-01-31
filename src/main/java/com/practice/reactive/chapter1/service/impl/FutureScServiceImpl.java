package com.practice.reactive.chapter1.service.impl;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import com.practice.reactive.chapter1.service.FutureScService;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class FutureScServiceImpl implements FutureScService {
    @Override
    public Future<Output> calculate(Input value) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new AsyncResult<>(new Output(value.getAmount(), value.getPrice()));
    }
}
