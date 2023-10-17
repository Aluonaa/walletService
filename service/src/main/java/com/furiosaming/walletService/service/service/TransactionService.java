package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.service.response.Response;

import java.util.List;

/**
 * Интерфейс сервиса транзакций
 */
public interface TransactionService {
    /**
     * Метод создания транзакции
     * @param person текущий пользователь
     * @param cash сумма транзакции
     * @param transactionId уникальный идентификатор транзакции
     * @param transactionType тип транзакции
     * @return созданная транзакция или ошибка
     */
    Response<Transaction> createTransaction(Person person, Long cash, Long transactionId, TransactionType transactionType);

    /**
     * Метод проверки уникальности кода транзакции
     * @param transactionCode код транзакции
     * @return найденная транзакция или ошибка
     */
    Response<Long> getTransactionByTransactionCode(Long transactionCode);

    /**
     * Получение всех транзакций банковского счета
     * @param id уникальный идентификатор банковского счета
     * @return список транзакций или описание ошибки
     */
    Response<List<Transaction>> getTransactionsByBankAccountId(Long id);
}
