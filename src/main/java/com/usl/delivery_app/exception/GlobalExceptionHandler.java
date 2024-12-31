package com.usl.delivery_app.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("message", "Request body is missing or malformed");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>("Null pointer exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>("USERNAME or PASSWORD NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException exception) {
        return new ResponseEntity<>("JWT NOT VALID", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException exception) {
        return new ResponseEntity<>(exception.getReason(), exception.getStatus());
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        return new ResponseEntity<>("Endpoint does not exist", HttpStatus.NOT_FOUND);
    }

    // Add more exception handlers as needed...
}