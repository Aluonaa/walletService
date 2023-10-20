package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.service.response.Response;

import java.util.List;

/**
 * Интерфейс сервиса транзакций
 */
public interface TransactionService {
    /**
     * Метод создания транзакции
     * @param transaction транзакция, которую необходимо создать
     * @return созданная транзакция или ошибка
     */
    Response<Transaction> createTransaction(Transaction transaction);

    /**
     * Метод проверки уникальности кода транзакции
     * @param transactionCode код транзакции
     * @return транзакция не найдена (успех) или ошибка
     */
    Response<Long> getTransactionByTransactionCode(Long transactionCode);

    /**
     * Получение всех транзакций банковского счета
     * @param id уникальный идентификатор банковского счета
     * @return список транзакций или описание ошибки
     */
    Response<List<Transaction>> getTransactionsByBankAccountId(Long id);
}