package com.product.eurukaclient.query;

import com.core.events.ProductReservedEvent;
import com.product.eurukaclient.core_events.ProductCreatedEvents;
import com.product.eurukaclient.enity.ProductEntity;
import com.product.eurukaclient.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductEventHandler {
    private final ProductsRepository productsRepository;

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){

    }

    @ExceptionHandler(resultType = Exception.class)
    public void handleException(Exception exception)throws Exception{
        throw exception;
    }
    @EventHandler
    public void on(ProductCreatedEvents events)throws Exception{
        ProductEntity product = new ProductEntity();
        BeanUtils.copyProperties(events,product);
        try {
            productsRepository.save(product);
        }catch (Exception e){
            throw new Exception("Forcing exception to event handler class");
        }

    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent){
        ProductEntity product = productsRepository.findByProductId(productReservedEvent.getProductId());
        product.setQuantity(product.getQuantity()-productReservedEvent.getQuantity());
        productsRepository.save(product);

    }
}
