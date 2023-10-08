package com.furiosaming.walletService.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String uuid;
    private String passport;
    private String login;
    private String password;
    private List<BankAccount> bankAccounts;
}
