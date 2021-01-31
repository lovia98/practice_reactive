package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;

import java.util.function.Consumer;

public interface ShoppingCardService {
   void calculate(Input value, Consumer<Output> c);
}
