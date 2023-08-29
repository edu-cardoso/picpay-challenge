package com.example.picpaychallenge.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String fullName;
  @Column(unique = true)
  private String document;
  @Column(unique = true)
  private String email;
  private String password;
  private BigDecimal balance;
  @Enumerated(EnumType.STRING)
  private UserType userType;

  public User() {
  }

  public User(UUID id, String fullName, String document, String email, String password, BigDecimal balance, UserType userType) {
    this.id = id;
    this.fullName = fullName;
    this.document = document;
    this.email = email;
    this.password = password;
    this.balance = balance;
    this.userType = userType;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;

    return getId().equals(user.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", fullName='" + fullName + '\'' +
            ", document='" + document + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", balance=" + balance +
            ", userType=" + userType +
            '}';
  }
}
