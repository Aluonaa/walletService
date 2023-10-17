package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.service.response.Response;


/**
 * Интерфейс сервиса банковского счета
 */
public interface BankAccountService {

    /**
     * Находит банковский счет по id
     * @param id уникальный идентификатор пользователя
     * @return банковский счет или описание ошибки
     */
    Response<BankAccount> getBankAccountByPersonId(Long id);

    /**
     * Метод создания банковского счета
     * @param person владелец аккаунта
     * @return созданный банковский счет или описание ошибки
     */
    Response<BankAccount> createBankAccount(Person person);

    /**
     * Метод вывода средств и пополнения счета
     * @param person текущий пользователь, от лица которого совершается операция
     * @param transactionCode уникальный код транзакции
     * @param cash сумма транзакции
     * @param transactionType тип транзакции
     * @return возвращает либо успешный статус операции и текущую сумму на счету,
     * либо описание ошибки
     */
    Response<Long> cashInOut(Person person, Long transactionCode,
                               Long cash, TransactionType transactionType);
}
