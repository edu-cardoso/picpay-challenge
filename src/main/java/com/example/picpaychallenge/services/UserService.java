package com.example.picpaychallenge.services;

import com.example.picpaychallenge.entities.User;
import com.example.picpaychallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public User findById(UUID id) {
    var user = repository.findById(id);
    return user.orElseThrow(() -> new RuntimeException("user not found"));
  }

  public void save(User user) {
    repository.save(user);
  }

}
