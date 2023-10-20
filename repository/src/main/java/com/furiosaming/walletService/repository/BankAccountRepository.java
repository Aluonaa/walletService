package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;

/**
 * Интерфейс репозитория банковского счета
 */
public interface BankAccountRepository {
    /**
     * Метод создает в базе данных банковский счет
     * @param person владелец аккаунта
     * @return созданный банковский счет
     */
    BankAccount createBankAccount(Person person);

    /**
     * Находит банковский счет по id в базе данных
     * @param id уникальный идентификатор пользователя
     * @return банковский счет или null
     */
    BankAccount getBankAccountByPersonId(Long id);
}
