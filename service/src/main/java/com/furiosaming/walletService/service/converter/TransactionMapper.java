package com.furiosaming.walletService.service.converter;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.service.dto.TransactionDto;

public class TransactionMapper {
    public static TransactionDto transactionToDtoMap(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setCashValue(transaction.getCashValue());
        transactionDto.setBankAccountDto(BankAccountMapper.bankAccountToDtoMap(transaction.getBankAccount()));
        return transactionDto;
    }

    public static Transaction dtoToTransactionMap(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setDate(transactionDto.getDate());
        transaction.setCashValue(transactionDto.getCashValue());
        transaction.setBankAccount(BankAccountMapper.dtoToBankAccountMap(transactionDto.getBankAccountDto()));
        return transaction;
    }
}
