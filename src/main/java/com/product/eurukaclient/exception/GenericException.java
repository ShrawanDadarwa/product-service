package com.product.eurukaclient.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class GenericException {
    private String message;
    private HttpHeaders headers;
    private HttpStatus httpStatus;
}
