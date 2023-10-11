package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.repository.TransactionRepository;

import java.util.List;
/**
 * Имплементация интерфейса репозитория транзакций
 */
public class TransactionRepositoryImpl implements TransactionRepository {
    /**
     * Метод имитирует создание в базе данных транзакции
     * @param transaction транзакция, которую необходимо сохранить
     * @param transactionList список транзакций конкретного пользователя
     *                        в который будет сохранена транзакция
     */
    public void createTransaction(Transaction transaction, List<Transaction> transactionList) {
        transactionList.add(transaction);
    }
}
