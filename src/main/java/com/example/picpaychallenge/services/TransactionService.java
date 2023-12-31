package com.example.picpaychallenge.services;

import com.example.picpaychallenge.dtos.TransactionDTO;
import com.example.picpaychallenge.dtos.TransactionResponseDTO;
import com.example.picpaychallenge.dtos.UserDTO;
import com.example.picpaychallenge.entities.Transaction;
import com.example.picpaychallenge.entities.User;
import com.example.picpaychallenge.entities.UserType;
import com.example.picpaychallenge.repositories.TransactionRepository;
import com.example.picpaychallenge.services.Exceptions.TransactionNotAllowedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import java.util.UUID;

@Service
public class TransactionService {

  @Autowired
  private UserService userService;

  @Autowired
  private TransactionRepository repository;

  @Autowired
  private RestTemplate restTemplate;

  public void verifyTransaction(User sender, User receiver, BigDecimal value) {
    if (sender.getUserType() == UserType.SHOPKEEPER) {
      throw new TransactionNotAllowedException("Lojistas não estão habilitados a realizar transferência");
    }

    if (sender.getBalance().compareTo(value) < 0) {
      throw new TransactionNotAllowedException("Saldo insuficiênte");
    }

    if (sender.getId() == receiver.getId()) {
      throw new TransactionNotAllowedException("Transferências não podem ser feitas para a própria conta");
    }
  }

  public void authorizeTransaction() {
    var response = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
    if (!response.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException("Transação não autorizada");
    }
  }

  public void changeUserBalance(User sender, User receiver, BigDecimal value) {
    sender.setBalance(sender.getBalance().subtract(value));
    receiver.setBalance(receiver.getBalance().add(value));

    userService.save(sender);
    userService.save(receiver);
  }

  public TransactionResponseDTO create(TransactionDTO transaction) {
    var sender = userService.findById(transaction.sender());
    var receiver = userService.findById(transaction.receiver());

    verifyTransaction(sender, receiver, transaction.value());

    var entity = new Transaction();
    BeanUtils.copyProperties(transaction, entity);
    entity.setTimestamp(Instant.now());

    authorizeTransaction();
    repository.save(entity);
    changeUserBalance(sender, receiver, transaction.value());

    return new TransactionResponseDTO(entity.getId(), new UserDTO(sender), new UserDTO(receiver), entity.getValue(), entity.getTimestamp());
  }

  public List<Transaction> findAll() {
    return repository.findAll();
  }
}
