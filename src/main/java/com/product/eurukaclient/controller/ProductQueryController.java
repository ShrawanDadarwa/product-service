package com.product.eurukaclient.controller;

import com.product.eurukaclient.model.ProductModel;
import com.product.eurukaclient.query.FindProductQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductQueryController {
    private final QueryGateway queryGateway;
    @GetMapping
    public List<ProductModel> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        List<ProductModel> productModelList =queryGateway.query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductModel.class)).join();
        return productModelList;
    }
}
