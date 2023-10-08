package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;

import java.util.List;

public interface BankAccountRepository {
    BankAccount getBankAccount(Long id, List<BankAccount> bankAccountList);
    List<BankAccount> getBankAccountsByPerson(String uuid, List<Person> personList);
    BankAccount createBankAccount(BankAccount bankAccount, List<BankAccount> bankAccountList);
    BankAccount authorize(String login, String password, List<BankAccount> bankAccountList);
}
