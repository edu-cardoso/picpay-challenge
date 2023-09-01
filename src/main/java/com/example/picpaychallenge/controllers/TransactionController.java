package com.example.picpaychallenge.controllers;

import com.example.picpaychallenge.dtos.TransactionDTO;
import com.example.picpaychallenge.dtos.TransactionResponseDTO;
import com.example.picpaychallenge.entities.Transaction;
import com.example.picpaychallenge.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired
  private TransactionService service;

  @PostMapping
  public ResponseEntity<TransactionResponseDTO> create(@RequestBody TransactionDTO transactionDto) {
    var transaction = service.create(transactionDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
  }

  @GetMapping
  public ResponseEntity<List<Transaction>> findAll() {
    var transactions = service.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(transactions);
  }
}
