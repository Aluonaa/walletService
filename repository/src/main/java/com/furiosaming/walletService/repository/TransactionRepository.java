package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.Transaction;

import java.util.List;
/**
 * Интерфейс репозитория транзакций
 */
public interface TransactionRepository {
    /**
     * Метод имитирует создание в базе данных транзакции
     * @param transaction транзакция, которую необходимо сохранить
     * @param transactionList список транзакций конкретного пользователя
     *                        в который будет сохранена транзакция
     */
    void createTransaction(Transaction transaction, List<Transaction> transactionList);
}
