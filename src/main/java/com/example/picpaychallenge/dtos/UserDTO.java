package com.example.picpaychallenge.dtos;

import com.example.picpaychallenge.entities.User;
import com.example.picpaychallenge.entities.UserType;

import java.math.BigDecimal;
import java.util.UUID;

public record UserDTO(UUID id, String fullName, String document, BigDecimal balance, UserType userType) {
  public UserDTO(User entity) {
    this(entity.getId(), entity.getFullName(), entity.getDocument(), entity.getBalance(), entity.getUserType());
  }
}
