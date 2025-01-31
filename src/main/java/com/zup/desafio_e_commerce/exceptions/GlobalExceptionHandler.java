package com.zup.desafio_e_commerce.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String CPF_ERROR_MESSAGE = "O CPF informado já está cadastrado no sistema.";
    private static final String EMAIL_ERROR_MESSAGE = "O email informado já está cadastrado no sistema.";
    private static final String NAME_ERROR_MESSAGE = "O nome informado já está cadastrado no sistema.";
    private static final String GENERIC_DB_ERROR_MESSAGE = "Ocorreu um erro de integridade no banco de dados. Verifique os dados enviados.";

    @ExceptionHandler({
            ClientNotFoundException.class,
            ProductNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(RuntimeException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            InvalidValueException.class,
            HttpMessageNotReadableException.class,
            UnregisterProductException.class,
            InsufficientStockException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(RuntimeException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String userFriendlyMessage = determineDataIntegrityErrorMessage(ex.getMessage());
        return buildErrorResponse(userFriendlyMessage, HttpStatus.BAD_REQUEST);
    }

    private String determineDataIntegrityErrorMessage(String message) {
        if (message.contains("CPF NULLS FIRST")) {
            return CPF_ERROR_MESSAGE;
        } else if (message.contains("EMAIL NULLS FIRST")) {
            return EMAIL_ERROR_MESSAGE;
        } else if (message.contains("NAME NULLS FIRST")) {
            return NAME_ERROR_MESSAGE;
        } else {
            return GENERIC_DB_ERROR_MESSAGE;
        }
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message);
        return new ResponseEntity<>(errorResponse, status);
    }
}