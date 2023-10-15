package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.service.response.Response;

import java.util.List;

/**
 * Интерфейс сервиса банковского счета
 */
public interface BankAccountService {
    /**
     * Метод создания банковского счета
     * @param id уникальный идентификатор счета
     * @param personList список всех пользователей
     * @return возвращает созданный счет или описание ошибки
     */
    Response<BankAccount> createBankAccount(Long id, List<Person> personList);

    /**
     * Метод вывода средств и пополнения счета
     * @param personList список пользователей
     * @param person текущий пользователь, от лица которого совершается операция
     * @param transactionId уникальный идентификатор транзакции
     * @param cash сумма транзакции
     * @param transactionType тип транзакции
     * @return возвращает либо успешный статус операции и текущую сумму на счету,
     * либо описание ошибки
     */
    Response<Long> cashInOut(List<Person> personList, Person person, Long transactionId,
                               Long cash, TransactionType transactionType);
}
