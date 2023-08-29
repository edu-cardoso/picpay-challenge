package com.example.picpaychallenge.services;

import com.example.picpaychallenge.dtos.TransactionDTO;
import com.example.picpaychallenge.entities.Transaction;
import com.example.picpaychallenge.entities.User;
import com.example.picpaychallenge.entities.UserType;
import com.example.picpaychallenge.repositories.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

  @Autowired
  private UserService userService;

  @Autowired
  private TransactionRepository repository;

  public void verifyTransaction(User sender, BigDecimal value) {
    if (sender.getUserType() == UserType.SHOPKEEPER) {
      throw new RuntimeException("Lojistas não estão habilitados a realizar transferência");
    }

    if (sender.getBalance().compareTo(value) < 0) {
      throw new RuntimeException("Saldo insuficiênte");
    }
  }

  public Transaction create(TransactionDTO transaction) {
    var sender = userService.findById(transaction.sender());
    var receiver = userService.findById(transaction.receiver());

    verifyTransaction(sender, transaction.value());

    var entity = new Transaction();
    BeanUtils.copyProperties(transaction, entity);

    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receiver.setBalance(receiver.getBalance().add(transaction.value()));

    repository.save(entity);
    userService.save(sender);
    userService.save(receiver);

    return entity;
  }
}
