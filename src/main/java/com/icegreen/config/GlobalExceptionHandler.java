package com.icegreen.config;

import com.icegreen.expeptions.IceCreamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

//    https://http.cat/

    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(final MethodArgumentNotValidException ex,
                                                        final WebRequest request) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> {
                var fieldName = error.getField();
                var errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IceCreamException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<?> handleIceCreamException(final IceCreamException ex,
                                                     final WebRequest request) {
        var errors = new HashMap<String, String>();
        errors.put("message", ex.getMessage());
        errors.put("status", HttpStatus.I_AM_A_TEAPOT.toString());
        errors.put("request", request.getDescription(false));
        errors.put("link", "https://http.cat/418");
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errors);
    }

}
