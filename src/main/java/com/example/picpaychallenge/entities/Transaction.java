package com.example.picpaychallenge.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
  private static final Long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private User sender;
  private User receiver;
  private BigDecimal value;

  public Transaction() {
  }

  public Transaction(UUID id, User sender, User receiver, BigDecimal value) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
    this.value = value;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Transaction{" +
            "id=" + id +
            ", sender=" + sender +
            ", receiver=" + receiver +
            ", value=" + value +
            '}';
  }
}
