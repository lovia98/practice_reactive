package com.practice.reactive.chapter1.service.impl;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import com.practice.reactive.chapter1.service.ShoppingCardService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Consumer;

/**
 * 비동기 처리로 shoppingCardService 이용하기
 */
@Service("asyncShoppingCardService")
public class AsyncShoppingCardService implements ShoppingCardService {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 처리 하는 부분을 별도의 쓰레드에서 처리 할 수 있지만,
     * 1. 쓰레드 풀에 대한 관리가 필요.
     * 2. javascript와 같은 콜백 지옥이 생길 수도 있을듯.
     *
     * @param value
     * @param c
     */
    @Override
    public void calculate(Input value, Consumer<Output> c) {
        new Thread(() -> {
            System.out.println("쓰레드를 별도로 생성하여 처리 한다. thread name : " + Thread.currentThread().getName());

            String queryUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/example1")
                    .queryParam("amount", value.getAmount())
                    .queryParam("price", value.getPrice())
                    .build()
                    .toUriString();

            Output result = restTemplate.getForObject(queryUrl, Output.class);
            c.accept(result);
        }).start();

        //테스트 코드 실행시 메인 쓰레드가 종료되면 끝나므로 sleep 타임을 줌
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("thread time 오류 발생");
        }
    }
}
