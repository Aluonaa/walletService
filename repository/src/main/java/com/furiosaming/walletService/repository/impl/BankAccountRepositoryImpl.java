package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.repository.BankAccountRepository;
/**
 * Имплементация интерфейса репозитория банковского счета
 */
public class BankAccountRepositoryImpl implements BankAccountRepository {
    /**
     * Метод имитирует создание в базе данных банковского счета
     * @param id уникальный идентификатор банковского счета
     * @return созданный банковский счет
     */
    public BankAccount createBankAccount(Long id) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(id);
        return bankAccount;
    }
}
