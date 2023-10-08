package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.repository.TransactionRepository;

import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    public Transaction createTransaction(Transaction transaction, List<Transaction> transactionList) {
        transactionList.add(transaction);
        return transaction;
    }
}
