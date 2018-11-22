package ru.firstline.studyapp.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.firstline.studyapp.exception.ApiErrorResponse;
import ru.firstline.studyapp.exception.NotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse defaultExceptionHandler(Exception ex) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withError_code(HttpStatus.BAD_REQUEST.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(ex.getMessage())
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiErrorResponse handleNotFoundException(NotFoundException ex) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withError_code(HttpStatus.NOT_FOUND.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(ex.getMessage())
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, EmptyResultDataAccessException.class})
    public ApiErrorResponse handleIllegalArgumentException(Exception ex) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withError_code(HttpStatus.BAD_REQUEST.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(ex.getMessage())
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ApiErrorResponse handleIllegalArgumentException(DataIntegrityViolationException ex) {
        return new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.CONFLICT)
                .withError_code(HttpStatus.CONFLICT.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(ex.getMessage())
                .build();
    }
}
