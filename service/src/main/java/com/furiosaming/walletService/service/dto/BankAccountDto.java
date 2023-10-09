package com.furiosaming.walletService.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    private Long id;
    private PersonDto person;
    private double cashValue;
}
