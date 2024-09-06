package com.publicis.footballstanding.exception;

import com.publicis.footballstanding.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDto> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception) {
        log.error("Missing Servlet Request Parameter Exception ::", exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDto.setMessage(
                String.format("Required parameter '%s' is not present", exception.getParameterName()));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        log.error("No Handler Found Exception ::", exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        log.error("Http Request Method Not Supported Exception ::", exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        errorDto.setError(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDto> handleServiceException(ServiceException exception) {
        log.error("Service Exception ::", exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorDto> handleRuntimeException(
            RuntimeException exception) {
        log.error("Runtime exception ::", exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
