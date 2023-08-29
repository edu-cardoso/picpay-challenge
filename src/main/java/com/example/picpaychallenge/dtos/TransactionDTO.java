package com.example.picpaychallenge.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(UUID sender, UUID receiver, BigDecimal value) {
}
