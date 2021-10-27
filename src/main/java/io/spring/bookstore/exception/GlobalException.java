package io.spring.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleError() {
        return new ResponseEntity<>("Something Were Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
