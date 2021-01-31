package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;

import java.util.concurrent.Future;

public interface FutureScService {

    Future<Output> calculate(Input value);
}
