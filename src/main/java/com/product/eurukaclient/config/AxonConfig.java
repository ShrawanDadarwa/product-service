package com.product.eurukaclient.config;

import com.product.eurukaclient.exceptionhandler.DefaultServiceExceptionHandler;
import com.product.eurukaclient.exceptionhandler.ProductServiceEventHandler;
import org.axonframework.config.ConfigurerModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public ConfigurerModule processingGroupErrorHandlingConfigurerModule() {
        return configurer -> configurer.eventProcessing(
                processingConfigurer -> processingConfigurer.registerDefaultListenerInvocationErrorHandler(
                        /* create listener error handler */
                                conf -> new DefaultServiceExceptionHandler()
                        )
                        // ... or for a specific processing group:
                        .registerListenerInvocationErrorHandler(
                                "product-group",
                                conf -> new ProductServiceEventHandler()
                        )
        );
    }
}
