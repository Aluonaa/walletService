package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.BankAccountRepository;

import java.util.List;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    public BankAccount getBankAccount(Long id, List<BankAccount> bankAccountList) {
        for(BankAccount bankAccount: bankAccountList){
            if(bankAccount.getId().equals(id)){
                return bankAccount;
            }
        }
        return null;
    }

    public List<BankAccount> getBankAccountsByPerson(String uuid, List<Person> personList) {
        for(Person person: personList){
            if(person.getUuid().equals(uuid)){
                return person.getBankAccounts();
            }
        }
        return null;
    }

    public BankAccount createBankAccount(BankAccount bankAccount, List<BankAccount> bankAccountList) {
        bankAccountList.add(bankAccount);
        return bankAccount;
    }

    public BankAccount authorize(String login, String password, List<BankAccount> bankAccountList) {
        for(BankAccount bankAccount: bankAccountList){
            if(bankAccount.getPerson().getLogin().equals(login)){
                if(bankAccount.getPerson().getPassword().equals(password)){
                    return bankAccount;
                }
                else return null;
            }
        }
        return null;
    }
}
