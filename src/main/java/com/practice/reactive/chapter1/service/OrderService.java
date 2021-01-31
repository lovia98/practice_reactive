package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import com.practice.reactive.chapter1.service.impl.AsyncShoppingCardService;
import com.practice.reactive.chapter1.service.impl.SyncShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 주문 서비스
 * <p>
 * 여러 방식의 ShoppingCardService(쇼핑카트에 담기)
 * 이용 방식을 보여줌
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final SyncShoppingCardService syncScService;
    private final AsyncShoppingCardService asyncScService;
    private final FutureShoppingCardService futureScService;

    /**
     * 콜백 방식의 shoppingCardservice 이용 - 동기처리
     */
    void callback_sync(Input input) {
        syncScService.calculate(input,
                output -> {
                    System.out.println(" ========================================================= ");
                    System.out.printf("콜백 처리로 장바구니에 상품 주문건 담기 : %s", output.getResult());
                    System.out.println(" ========================================================= ");
                });
    }

    /**
     * 콜백 방식의 shoppingCardservice 이용 - 비동기처리
     *  - thread 관리가 필요하다.
     *  - 콜백 지옥이 생길 수 있다.
     *
     */
    void callback_async(Input input) {

        System.out.println("main thread : " + Thread.currentThread().getName());

        asyncScService.calculate(input,
                output -> {
                    System.out.println(" ========================================================= ");
                    System.out.printf("콜백 처리로 장바구니에 상품 주문건 담기 - 비동기 : %s\n", output.getResult());
                    System.out.println(" ========================================================= ");
                });
    }

    /**
     * Future를 통한 비동기
     *  - 콜백 지옥를 피하고, output 처리 대한 구현을 Future 구현으로 숨길수 있다.
     *  - get 라는 함수를 호출할때까지 다른일을 할수는 있지만, 그때까지 응답을 기다려야 한다는 점에서
     *    완전한 비동기 처리를 기대하긴 어렵다. (아래 코드에서 2초뒤 실행 결과를 먼저 get하고, 5초뒤 다른 결과의 응답을 가져올 수 없다는 점)
     *  - java8 에서는 이런 단점을 보안하기 위해 CompletableFuture 라는 것을 제공한다.
     */
    void use_future_process(Input input) throws ExecutionException, InterruptedException, TimeoutException {
        Future<Output> future = futureScService.calculate(input);
        Output output = future.get(5, TimeUnit.SECONDS);
        Output output1 = future.get(2, TimeUnit.SECONDS);
        System.out.printf("Future를 이용한 비동기 방식으로 장바구니에 상품 주문건 담기 5초 후 결과 : %s\n", output.getResult());
        System.out.printf("Future를 이용한 비동기 방식으로 장바구니에 상품 주문건 담기 2초 후 결과 : %s\n", output1.getResult());
    }
}
