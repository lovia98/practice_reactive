package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import com.practice.reactive.chapter1.service.impl.AsyncShoppingCardService;
import com.practice.reactive.chapter1.service.impl.SyncShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

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
    private final FutureScService futureScService;
    private final CompletableFutureScService completableFutureScService;

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
     * - thread 관리가 필요하다.
     * - 콜백 지옥이 생길 수 있다.
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
     * - 콜백 지옥를 피하고, output 처리 대한 구현을 Future 구현으로 숨길수 있다.
     * - get 라는 함수를 호출할때까지 다른일을 할수는 있지만, 그때까지 응답을 기다려야 한다는 점에서
     * 완전한 비동기 처리를 기대하긴 어렵다. (아래 코드에서 2초뒤 실행 결과를 먼저 get하고, 5초뒤 다른 결과의 응답을 가져올 수 없다는 점)
     * - java8 에서는 이런 단점을 보안하기 위해 CompletableFuture 라는 것을 제공한다.
     */
    void future_process(Input input) throws ExecutionException, InterruptedException, TimeoutException {
        Future<Output> future = futureScService.calculate(input);
        Output output = future.get(5, TimeUnit.SECONDS);
        Output output1 = future.get(2, TimeUnit.SECONDS);
        System.out.printf("Future를 이용한 비동기 방식으로 장바구니에 상품 주문건 담기 5초 후 결과 : %s\n", output.getResult());
        System.out.printf("Future를 이용한 비동기 방식으로 장바구니에 상품 주문건 담기 2초 후 결과 : %s\n", output1.getResult());
    }

    /**
     * 비동기 처리를 선언적 명령어(함수형 스타일)로 실행함.
     * 코드가 좀더 깔끔해 보이고 진정한? 비동기 처리가 가능하다.
     * 여러개의 비동기 요청에 대한 병렬 처리를 쉽게 control 할수 있는 여러 메소드들을 제공해 준다.
     *
     * 이전 멀티 쓰레드 처리에 비해서 편하지만, 여전히 쓰레드 풀 관리를 잘해주지 않으면
     * 문제가 발생하게 된다.
     */
    void completableFuture_process(Input input1, Input input2) {

        CompletableFuture<Output> price1 = completableFutureScService.calculate(input1);
        CompletableFuture<Output> price2 = completableFutureScService.calculate(new Input(input2.getAmount(), input2.getPrice()));

        price2.thenCombineAsync(price1, (a, b) -> new Output(a.getResult() + b.getResult()))
                .thenAcceptAsync(result -> System.out.println("두개의 쓰레드가 완료된 후에 조합된 값을 처리 : " + result.getResult()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
