package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;

import java.util.concurrent.CompletableFuture;

public interface CompletableFutureScService {

    CompletableFuture<Output> calculate(Input value);
}
