package com.practice.reactive.chapter1.service.impl;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import com.practice.reactive.chapter1.service.ShoppingCardService;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * 동기방식으로 shoppingCardService 처리 하기
 */
@Service("syncShoppingCardService")
public class SyncShoppingCardService implements ShoppingCardService {

    @Override
    public void calculate(Input value, Consumer<Output> c) {
        Output output = new Output(value.getAmount(), value.getPrice());
        c.accept(output);
    }
}
