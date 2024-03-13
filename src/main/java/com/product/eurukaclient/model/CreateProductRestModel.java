package com.product.eurukaclient.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRestModel {
//    @NotEmpty(message = "Product Title is required for each product")
    private String title;
    @Min(value = 1,message = "Price can not be less than 1")
    private BigDecimal price;
    @Min(value = 1,message = "Price can not be less than 1")
    @Max(value = 5,message = "Price can not be less than 5")
    private Integer quantity;
}
