package com.example.picpaychallenge.repositories;

import com.example.picpaychallenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
