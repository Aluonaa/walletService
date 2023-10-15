package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;

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
     */
    void createTransaction(Person person, Long cash, Long transactionId, TransactionType transactionType);
}
