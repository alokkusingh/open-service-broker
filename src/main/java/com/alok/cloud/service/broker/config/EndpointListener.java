package com.alok.cloud.service.broker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointListener {

    @Autowired
    private RequestMappingHandlerMapping  requestMappingHandlerMapping;
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
//        ApplicationContext applicationContext = event.getApplicationContext();
//        applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
//                .forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

    }

}
