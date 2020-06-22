package com.rafaelsouzaf.fbe.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    Integer httpCode;
    Error error;

    public ErrorMessage(String message, String details, HttpStatus httpStatus) {
        this.error = new Error(message, details);
        this.httpCode = httpStatus.value();
    }

    @Getter
    @Setter
    private class Error {
        String message;
        String details;
        Date timestamp = new Date();

        public Error(String message, String details) {
            this.message = message;
            this.details = details;
        }
    }

}
