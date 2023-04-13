package com.ihub.linemanagementservice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity handleMethodArgumentNotFound(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
            err -> errors.put(
                    err.getField(),
                    err.getDefaultMessage()
            )
        );
        ValidationError apiError = new ValidationError(
                request.getRequestURI(),
                HttpStatus.CONFLICT,
                "Data integrity violation",
                List.of(errors)
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity handleEntityNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
            request.getRequestURI(),
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity(apiError, apiError.getStatus());
    }

}
