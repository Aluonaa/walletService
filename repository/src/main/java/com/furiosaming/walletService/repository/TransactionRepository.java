package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction createTransaction(Transaction transaction, List<Transaction> transactionList);
}
