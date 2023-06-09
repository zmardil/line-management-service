package com.ihub.linemanagementservice.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

@Data
public class ValidationError extends ApiError {

    private List<HashMap<String, String>> errors;

    public ValidationError(String path, HttpStatus status, String message, List<HashMap<String, String>> errors) {
        super(path, status, message);
        this.errors = errors;
    }
}
