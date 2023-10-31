package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.Transaction;

import java.util.List;

/**
 * Интерфейс репозитория транзакций
 */
public interface TransactionRepository {
    /**
     * Метод создает в базе данных транзакцию
     * @param transaction транзакция, которую необходимо сохранить
     */
    Transaction createTransaction(Transaction transaction);

    /**
     * Метод ищет транзакцию в базе данных с совпадающим кодом транзакции
     * @param transactionCode код транзакции
     * @return возвращает тип boolean (найдена транзакция или нет)
     */
    Boolean isPresentTransactionByTransactionCode(Long transactionCode);

    /**
     * Получение всех транзакций банковского счета из базы данных
     * @param id уникальный идентификатор банковского счета
     * @return список транзакций
     */
    List<Transaction> getTransactionsByBankAccountId(Long id);
}
