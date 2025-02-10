package com.eaa.controller;

import com.eaa.dto.OrderResponseDTO;
import com.eaa.service.SwiggyAppService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swiggy")
@Slf4j
public class SwiggyAppController {

    @Autowired
    private SwiggyAppService service;

    @GetMapping("/home")
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO checkOrderStatus(@PathVariable String orderId ,
                                             @RequestHeader("loggedInUser") String username,
                                             @RequestHeader("correlationId") String correlationId) throws Exception {
        log.info("logged in user details : {}", username);
        MDC.put("correlationId", correlationId);
        log.info("correlationId : {}", correlationId);
        return service.checkOrderStatus(orderId);
    }
}
