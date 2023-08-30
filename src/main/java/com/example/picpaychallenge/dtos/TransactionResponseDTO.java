package com.example.picpaychallenge.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponseDTO(UUID id, UserDTO sender, UserDTO receiver, BigDecimal value) {
}
