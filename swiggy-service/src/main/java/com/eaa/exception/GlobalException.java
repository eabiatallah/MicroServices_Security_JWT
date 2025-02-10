package com.eaa.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(SwiggyServiceException.class)
    public ResponseEntity<?> handleSwiggyServiceException(SwiggyServiceException ex) throws JsonProcessingException {
        log.error("SwiggyServiceGlobalExceptionHandler::handleSwiggyServiceException exception caught {}", ex.getMessage());
        CustomErrorResponse errorResponse = new ObjectMapper().readValue(ex.getMessage(), CustomErrorResponse.class);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGenericException(Exception ex) {
//        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .errorCode("SWIGGY-SERVICE-2002")
//                .errorMessage(ex.getMessage())
//                .build();
//        log.error("SwiggyServiceGlobalExceptionHandler::handleGenericException exception caught {}", ex.getMessage());
//        return ResponseEntity.internalServerError().body(errorResponse);
//    }
}
