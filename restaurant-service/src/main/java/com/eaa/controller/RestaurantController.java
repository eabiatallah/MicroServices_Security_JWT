package com.eaa.controller;

import com.eaa.dto.OrderResponseDTO;
import com.eaa.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RefreshScope
@Slf4j
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("${my.common: default value}")
    private String commonMessage;

    @Value("${server.port: 8000}")
    private String port;

    @Value("${my.password: 1}")
    private String password;

    @GetMapping
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/orders/status/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable String orderId, @RequestHeader("X-Correlation-ID") String correlationId) {
        MDC.put("correlationId", correlationId);
        log.info("info log, correlationId {}",correlationId );
        log.debug("debug log, correlationId {}",correlationId);
        return service.getOrder(orderId);
    }

    @GetMapping("/greeting")
    public String greeting()
    {
        return "my.greeting: "+greetingMessage+" and common value: "+commonMessage+" and server port is "+ port+" and pwd is "+password;
    }
}
