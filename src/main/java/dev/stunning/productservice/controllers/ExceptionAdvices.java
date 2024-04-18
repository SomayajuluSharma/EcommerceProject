package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> stunning(Exception exception){
        return new ResponseEntity<>("Not found", HttpStatus.OK);
    }
}
