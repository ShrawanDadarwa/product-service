package com.product.eurukaclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


public class TestController {

    @GetMapping
    public Mono<?> getAlter(){
        return Mono.just("Hello GET Products");
    }
}
