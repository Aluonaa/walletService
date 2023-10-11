package com.furiosaming.walletService.service.service.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.TransactionRepository;
import com.furiosaming.walletService.service.service.TransactionService;

import java.util.Date;

/**
 * Имплементация интерфейса сервиса транзакций
 */
public class TransactionServiceImpl implements TransactionService {

    /** Внедрение зависимости репозитория транзакций */
    TransactionRepository transactionRepository;

    /** Конструктор класса */
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    /**
     * Метод создания транзакции
     * @param person текущий пользователь
     * @param cash сумма транзакции
     * @param transactionId уникальный идентификатор транзакции
     * @param transactionType тип транзакции
     * @return возвращает зарегистрированную транзакцию
     */
    @Override
    public Transaction createTransaction(Person person,
            Double cash, Long transactionId, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setTransactionType(transactionType);
        transaction.setCashValue(cash);
        transaction.setDate(new Date());
        transactionRepository.createTransaction(transaction, person.getBankAccount().getTransactions());
        return transaction;
    }
}
