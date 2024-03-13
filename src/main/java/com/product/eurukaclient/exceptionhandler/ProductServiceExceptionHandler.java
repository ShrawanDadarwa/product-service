package com.product.eurukaclient.exceptionhandler;

import com.product.eurukaclient.exception.GenericException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ProductServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Mono<?> handleIllegalStateException(RuntimeException ex) {
        return Mono.just(GenericException.builder().message(ex.getMessage()).headers(new HttpHeaders()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @ExceptionHandler(value = {Exception.class})
    public Mono<?> handleOtherExceptions(RuntimeException ex) {
        return Mono.just(GenericException.builder().message(ex.getMessage()).headers(new HttpHeaders()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
