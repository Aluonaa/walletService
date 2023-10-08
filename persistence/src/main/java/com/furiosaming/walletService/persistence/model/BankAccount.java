package com.furiosaming.walletService.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private Long id;
    private Person person;
    private double cashValue;
    private List<Transaction> transactions;
    private List<AccountAction> accountActions;
}
