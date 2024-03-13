package com.product.eurukaclient.query;

import com.product.eurukaclient.core_events.ProductCreatedEvents;
import com.product.eurukaclient.enity.ProductLookUpEntity;
import com.product.eurukaclient.repository.ProductLookUpRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookUpEventHandler {
    private final ProductLookUpRepository productLookUpRepository;
    @EventHandler
    public void on(ProductCreatedEvents events){
        ProductLookUpEntity productLookUpEntity = new ProductLookUpEntity();
        productLookUpEntity.setProductId(events.getProductId());
        productLookUpEntity.setTitle(events.getTitle());
        productLookUpRepository.save(productLookUpEntity);
    }
}
