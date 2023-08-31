package com.example.picpaychallenge.services.Exceptions;

public class TransactionNotAllowedException extends RuntimeException {
  public TransactionNotAllowedException(String message) {
    super(message);
  }
}
