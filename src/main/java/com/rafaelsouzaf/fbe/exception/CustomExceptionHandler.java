package com.rafaelsouzaf.fbe.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // 405
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 415
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}