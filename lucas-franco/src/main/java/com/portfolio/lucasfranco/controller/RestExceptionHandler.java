package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.APIErrorDTO;
import com.portfolio.lucasfranco.exceptions.NotAcceptable;
import com.portfolio.lucasfranco.exceptions.ParamBadRequest;
import com.portfolio.lucasfranco.exceptions.ParamNotFound;
import com.portfolio.lucasfranco.exceptions.Unauthorized;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;



@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ParamNotFound.class})
    protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
        APIErrorDTO apiError = new APIErrorDTO(
                NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("Param Not Found"));
        apiError.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), NOT_FOUND, request);
    }
    @ExceptionHandler(value = {ParamBadRequest.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        APIErrorDTO errorDTO = new APIErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Bad Request"));
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = {Unauthorized.class})
    protected ResponseEntity<Object> handleUnauthorized(RuntimeException ex, WebRequest request) {
            APIErrorDTO errorDTO = new APIErrorDTO(
                    HttpStatus.UNAUTHORIZED,
                    ex.getMessage(),
                    Arrays.asList("Unauthorized"));
            return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleForbidden(RuntimeException ex, WebRequest request) {
        APIErrorDTO errorDTO = new APIErrorDTO(
                HttpStatus.FORBIDDEN,
                ex.getMessage(),
                Arrays.asList("Forbidden"));
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = NotAcceptable.class)
    protected ResponseEntity<Object> handleNotAcceptable(Exception ex, WebRequest request) {
        APIErrorDTO errorDTO = new APIErrorDTO(
                HttpStatus.NOT_ACCEPTABLE,
                ex.getMessage(),
                Arrays.asList("Not Acceptable"));
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
        APIErrorDTO errorDTO = new APIErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                Arrays.asList("Exception"));

        return new ResponseEntity<Object>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handler for @Valid annotation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors= new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": "+ error.getDefaultMessage());
        }
        for(ObjectError error : ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName()+ ": "+ error.getDefaultMessage());
        }
        APIErrorDTO apiError= new APIErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(value= {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
        String bodyOfResponse= "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
