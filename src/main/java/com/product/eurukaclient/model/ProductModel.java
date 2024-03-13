package com.product.eurukaclient.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class ProductModel {
    private  String productId;
    private  String title;
    private  BigDecimal price;
    private  Integer quantity;
}
