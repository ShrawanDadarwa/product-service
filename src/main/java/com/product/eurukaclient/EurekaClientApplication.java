package com.product.eurukaclient;

import com.product.eurukaclient.commandInterceptorMessage.ProductCommandInterceptor;
import com.product.eurukaclient.exceptionhandler.ProductServiceEventHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
	@Autowired
	public void registerCommandCreateInterceptor(ApplicationContext context, CommandBus bus){
		bus.registerDispatchInterceptor(context.getBean(ProductCommandInterceptor.class));
	}
	/*@Autowired
	public void config(EventProcessingConfigurer config){
	config.registerListenerInvocationErrorHandler("product-group",configuration -> new ProductServiceEventHandler());
	}
	@Autowired
	public void configure(EventProcessingConfigurer config) {
		config.usingSubscribingEventProcessors();
	}*/
}
