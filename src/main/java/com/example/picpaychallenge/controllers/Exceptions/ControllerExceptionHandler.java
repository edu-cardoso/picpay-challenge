package com.example.picpaychallenge.controllers.Exceptions;

import com.example.picpaychallenge.services.Exceptions.NotFoundException;
import com.example.picpaychallenge.services.Exceptions.TransactionNotAllowedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<StandardError> entityNotFound(NotFoundException e, HttpServletRequest request) {
    var err = new StandardError();
    err.setStatus(HttpStatus.NOT_FOUND.value());
    err.setMessage(e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }
  @ExceptionHandler(TransactionNotAllowedException.class)
  public ResponseEntity<StandardError> unauthorizedTransaction(TransactionNotAllowedException e, HttpServletRequest request) {
    var err = new StandardError();
    err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    err.setMessage(e.getMessage());

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
  }

}
