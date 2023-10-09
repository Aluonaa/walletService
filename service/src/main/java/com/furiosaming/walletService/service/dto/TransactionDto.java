package com.furiosaming.walletService.service.dto;

import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private TransactionType transactionType;
    private double cashValue;
    private Date date;
    private BankAccountDto bankAccountDto;
}
