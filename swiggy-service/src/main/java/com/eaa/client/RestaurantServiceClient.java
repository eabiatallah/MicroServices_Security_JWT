package com.eaa.client;

import com.eaa.dto.OrderResponseDTO;
import com.eaa.exception.SwiggyServiceException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
@Slf4j
public class RestaurantServiceClient {
    @Autowired
    private RestTemplate template;

    private int attempt=1;

    //@Retry(name = "serviceA")
    //@CircuitBreaker(name = "serviceA", fallbackMethod = "serviceAFallback")
    //@RateLimiter(name = "serviceA")
    public OrderResponseDTO fetchOrderStatus(String orderId) throws Exception {
        //System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
        ResponseEntity<OrderResponseDTO> response = null;
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("X-Correlation-ID", MDC.get("correlationId"));
            HttpEntity entity = new HttpEntity(headers);
            //orderResponseDTO = template.getForObject("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId, OrderResponseDTO.class, correlationId);
             response = template.exchange(
                    "http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId,
                    HttpMethod.GET,
                    entity,
                    OrderResponseDTO.class
            );
        } catch (HttpServerErrorException errorException ){
            log.error("RestaurantServiceClient::fetchOrderStatus caught the HttpServer server error {}", errorException.getResponseBodyAsString());
            throw new SwiggyServiceException(errorException.getResponseBodyAsString());
        }
        catch (Exception ex) {
            log.error("RestaurantServiceClient::fetchOrderStatus caught the generic error {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        return response.getBody();
    }

//    public OrderResponseDTO serviceAFallback(Exception e ){
//        OrderResponseDTO dto = new OrderResponseDTO();
//        dto.setOrderId("101");
//        dto.setName("Circuit Breaker");
//        return dto;
//    }

}
