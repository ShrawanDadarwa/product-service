package com.product.eurukaclient.commandInterceptorMessage;

import com.product.eurukaclient.command.CreateProductCommand;
import com.product.eurukaclient.enity.ProductLookUpEntity;
import com.product.eurukaclient.repository.ProductLookUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final ProductLookUpRepository productLookUpRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return ((index, commandMessage) -> {
            log.info("Interceptor Payload : ", commandMessage.getPayloadType());
            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {

                CreateProductCommand commandMessagePayload = (CreateProductCommand) commandMessage.getPayload();
                ProductLookUpEntity byProductIdOrTitle = productLookUpRepository.findByProductIdOrTitle(commandMessagePayload.getProductId(), commandMessagePayload.getTitle());
                if (byProductIdOrTitle != null) {
                    throw
                    new IllegalArgumentException(String.format("Product with productId %s and Product Title %s are already available ",
                            commandMessagePayload.getProductId(), commandMessagePayload.getTitle()));
                }
            }
            return commandMessage;
        });
    }
}
