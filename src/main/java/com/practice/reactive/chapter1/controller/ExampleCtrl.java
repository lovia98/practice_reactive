package com.practice.reactive.chapter1.controller;

import com.practice.reactive.chapter1.model.Input;
import com.practice.reactive.chapter1.model.Output;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleCtrl {

    @GetMapping("/example1")
    public ResponseEntity<Output> example1(Input input) {
        return new ResponseEntity<>(new Output(input.getAmount() * input.getPrice()), HttpStatus.OK);
    }
}
