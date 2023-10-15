package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.BankAccount;

/**
 * Интерфейс репозитория банковского счета
 */
public interface BankAccountRepository {
    /**
     * Метод имитирует создание в базе данных банковского счета
     * @param id уникальный идентификатор банковского счета
     * @return созданный банковский счет
     */
    BankAccount createBankAccount(Long id);
}
