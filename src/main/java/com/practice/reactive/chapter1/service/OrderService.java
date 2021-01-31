package com.practice.reactive.chapter1.service;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.service.impl.AsyncShoppingCardService;
import com.practice.reactive.chapter1.service.impl.SyncShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    /**
     * 콜백 방식의 shoppingCardservice 이용 - 동기처리
     */
    void use_callback_sync(Input input) {
        syncScService.calculate(input,
                output -> {
                    System.out.println(" ========================================================= ");
                    System.out.printf("콜백 처리로 장바구니에 상품 주문건 담기 : %s", output.getResult());
                    System.out.println(" ========================================================= ");
                });
    }

    /**
     * 콜백 방식의 shoppingCardservice 이용 - 비동기처리
     */
    void use_callback_async(Input input) {

        System.out.println("main thread : " + Thread.currentThread().getName());

        asyncScService.calculate(input,
                output -> {
                    System.out.println(" ========================================================= ");
                    System.out.printf("콜백 처리로 장바구니에 상품 주문건 담기 - 비동기 : %s\n", output.getResult());
                    System.out.println(" ========================================================= ");
                });
    }
}
