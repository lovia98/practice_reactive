package com.practice.reactive.chapter1.service.impl;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import com.practice.reactive.chapter1.service.CompletableFutureScService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class CompletableFutureScServiceImpl implements CompletableFutureScService {
    @Override
    public CompletableFuture<Output> calculate(Input value) {
        return CompletableFuture.supplyAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("CompletableFuture 실행 완료 : " + Thread.currentThread().getName());
            return new Output(value.getAmount(), value.getPrice());
        });
    }
}
