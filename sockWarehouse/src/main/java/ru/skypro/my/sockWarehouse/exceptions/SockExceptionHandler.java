package ru.skypro.my.sockWarehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SockExceptionHandler {

    @ExceptionHandler(SockQuantityException.class)
    public ResponseEntity<?> handleNotFound(SockQuantityException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Недостаточно носков на складе");
    }

    @ExceptionHandler(SockPresentsException.class)
    public ResponseEntity<?> handleNotFound(SockPresentsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Насков с такими данными нет на складе");
    }
}
