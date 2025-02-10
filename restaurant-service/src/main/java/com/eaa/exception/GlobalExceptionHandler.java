package com.eaa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String ERROR_ORDER_NOT_FOUND = "RESTAURANT-SERVICE-1000";
    public static final String GENERIC_ERROR = "RESTAURANT-SERVICE-1001";

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException ex){
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode(ERROR_ORDER_NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();
        log.error("GlobalExceptionHandler::handleOrderNotFoundException exception caught {}",ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex){
        CustomErrorResponse errorResponse= CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode(GENERIC_ERROR)
                .errorMessage(ex.getMessage())
                .build()  ;
        log.error("RestaurantServiceGlobalExceptionHandler::handleGenericException exception caught {}",ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
