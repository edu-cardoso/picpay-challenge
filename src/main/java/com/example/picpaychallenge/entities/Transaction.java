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
  private UUID sender;
  private UUID receiver;
  private BigDecimal value;

  public Transaction() {
  }

  public Transaction(UUID id, UUID sender, UUID receiver, BigDecimal value) {
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

  public UUID getSender() {
    return sender;
  }

  public void setSender(UUID sender) {
    this.sender = sender;
  }

  public UUID getReceiver() {
    return receiver;
  }

  public void setReceiver(UUID receiver) {
    this.receiver = receiver;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction that)) return false;

    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
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

