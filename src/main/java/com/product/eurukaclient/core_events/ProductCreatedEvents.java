package com.product.eurukaclient.core_events;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductCreatedEvents {
    private  String productId;
    private  String title;
    private  BigDecimal price;
    private  Integer quantity;
}
