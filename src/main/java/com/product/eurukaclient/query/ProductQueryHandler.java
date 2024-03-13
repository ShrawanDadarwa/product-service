package com.product.eurukaclient.query;

import com.product.eurukaclient.enity.ProductEntity;
import com.product.eurukaclient.model.ProductModel;
import com.product.eurukaclient.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {
    private final ProductsRepository productsRepository;
    @QueryHandler
   public List<ProductModel> findProducts(FindProductQuery query){
        List<ProductModel> productModelList = new ArrayList<>();
        for(ProductEntity product:productsRepository.findAll()){
            ProductModel productModel = ProductModel.builder().build();
            BeanUtils.copyProperties(product,productModel);
            productModelList.add(productModel);
        }
        return productModelList;
   }
}
