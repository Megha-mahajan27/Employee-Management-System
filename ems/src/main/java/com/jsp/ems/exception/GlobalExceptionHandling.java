package com.jsp.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> invalidData(MethodArgumentNotValidException exception) {
        // Collect all field error messages
        List<String> errors = exception.getBindingResult()
                                       .getFieldErrors()
                                       .stream()
                                       .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                       .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> invalidOtp(InvalidOtpException invalidOtpException) {
        return new ResponseEntity<>(invalidOtpException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}