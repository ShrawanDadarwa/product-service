package com.product.eurukaclient.command;

import com.core.commands.ReserveProductCommand;
import com.core.events.ProductReservedEvent;
import com.product.eurukaclient.core_events.ProductCreatedEvents;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventUtils;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregrate {
    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregrate() {
    }

    @CommandHandler
    public ProductAggregrate(CreateProductCommand createProductCommand) {
        //Validate Create Product Command
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price can not less than or equal to zero");
        }

        if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Product Title can not be empty");
        }
        ProductCreatedEvents productCreatedEvents = new ProductCreatedEvents();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvents);
        AggregateLifecycle.apply(productCreatedEvents);
    }

    @CommandHandler
    public void handler(ReserveProductCommand reserveProductCommand) {
        if (quantity < reserveProductCommand.getQuantity()) {
            throw new IllegalArgumentException("Insufficient number of items in stocks");
        }
        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .productId(reserveProductCommand.getProductId())
                .orderId(reserveProductCommand.getOrderId())
                .quantity(reserveProductCommand.getQuantity())
                .userId(reserveProductCommand.getUserId()).build();
        AggregateLifecycle.apply(productReservedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvents productCreatedEvents) {
        this.productId = productCreatedEvents.getProductId();
        this.price = productCreatedEvents.getPrice();
        this.quantity = productCreatedEvents.getQuantity();
        this.title = productCreatedEvents.getTitle();
    }
    @EventSourcingHandler
    private void on(ProductReservedEvent productReservedEvent){
        this.quantity-=productReservedEvent.getQuantity();
    }
}
