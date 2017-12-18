package com.geolisa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 据说关键处理在 ResponseEntityExceptionHandler.
 * ControllerAdvice 专门用于处理restController 异常的 Component
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("handleResourceNotFound exception message is {}", ex.getMessage());
        }
        return handleExceptionInternal(ex, new ErrorResponseInfo(ex.getMessage()),
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
