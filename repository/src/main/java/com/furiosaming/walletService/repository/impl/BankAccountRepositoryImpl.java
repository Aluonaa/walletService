package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.BankAccountRepository;

import java.util.List;
import java.util.Optional;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    public BankAccount getBankAccount(Long id, List<BankAccount> bankAccountList) {
        Optional<BankAccount> result = bankAccountList.stream().filter(bankAccount -> id.equals(bankAccount.getId())).findFirst();
        return result.orElse(null);
    }

    public List<BankAccount> getBankAccountsByPerson(String uuid, List<Person> personList) {
        Optional<Person> result = personList.stream().filter(person -> uuid.equals(person.getUuid())).findFirst();
        return result.map(Person::getBankAccounts).orElse(null);
    }

    public BankAccount createBankAccount(BankAccount bankAccount, List<BankAccount> bankAccountList) {
        bankAccountList.add(bankAccount);
        return bankAccount;
    }
}
