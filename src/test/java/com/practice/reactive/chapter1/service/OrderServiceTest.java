package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void injection_test() {
        Assertions.assertNotNull(orderService);
    }

    @Test
    void callback_test() {
        orderService.callback_sync(new Input(3, 2000));
    }

    @Test
    void callback_test_async() {
        orderService.callback_async(new Input(3, 1000));
    }

    @Test
    void future_test_async() throws InterruptedException, ExecutionException, TimeoutException {
        orderService.use_future_process(new Input(3, 1000));
    }
}