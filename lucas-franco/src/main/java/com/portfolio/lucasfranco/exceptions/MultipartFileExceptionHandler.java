package com.portfolio.lucasfranco.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestControllerAdvice
public class MultipartFileExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handlerUploadFileException(MultipartFileExceptionHandler exception, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return "File Size limit exceeded. Please make sure the file siz is well within 250KB.";
    }
}
