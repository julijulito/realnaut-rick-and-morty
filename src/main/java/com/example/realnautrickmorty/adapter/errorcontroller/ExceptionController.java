package com.example.realnautrickmorty.adapter.errorcontroller;

import com.example.realnautrickmorty.domain.error.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<BaseErrorResponse> handleException(final ResponseStatusException e) {
        log.error(String.format("Request failed. Reason: %s", e.getReason()));
        return new ResponseEntity<>(
                new BaseErrorResponse(
                        e.getStatusCode(),
                        e.getReason()),
                e.getStatusCode());
    }

}
