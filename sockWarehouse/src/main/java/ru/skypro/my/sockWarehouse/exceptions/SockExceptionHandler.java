package ru.skypro.my.sockWarehouse.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SockExceptionHandler {

    @ExceptionHandler(SockQuantityException.class)
    public ResponseEntity<?> handleNotFound(SockQuantityException e) {
        log.error("Ошибка - недостаточно носков на складе");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Недостаточно носков на складе");
    }

    @ExceptionHandler(SockPresentsException.class)
    public ResponseEntity<?> handleNotFound(SockPresentsException e) {
        log.error("Ошибка - носков нет на складе");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Насков с такими данными нет на складе");
    }

    @ExceptionHandler(SockInputsException.class)
    public ResponseEntity<?> handleNotFound(SockInputsException e) {
        log.error("Ошибка ввода данных");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Введены неверные данные");
    }
}
