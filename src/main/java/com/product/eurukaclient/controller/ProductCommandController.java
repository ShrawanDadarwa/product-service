package com.product.eurukaclient.controller;

import com.product.eurukaclient.command.CreateProductCommand;
import com.product.eurukaclient.model.CreateProductRestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductCommandController {
    private final Environment environment;
    private final CommandGateway commandGateway;
    @PostMapping
    public Mono<?> createProducts(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder().productId(UUID.randomUUID().toString())
                .price(createProductRestModel.getPrice()).title(createProductRestModel.getTitle())
                .quantity(createProductRestModel.getQuantity()).build();
        //Command gateway is basically used to send an object to command bus
        String returnvalue =commandGateway.sendAndWait(createProductCommand);;
        /*try {
            returnvalue = commandGateway.sendAndWait(createProductCommand);
        }catch (Exception e){
            returnvalue =e.getMessage();
        }*/

        return Mono.just(returnvalue);
    }
}
