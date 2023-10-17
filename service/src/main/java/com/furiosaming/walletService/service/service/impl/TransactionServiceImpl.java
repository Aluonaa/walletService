package com.furiosaming.walletService.service.service.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.TransactionRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.TransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация интерфейса сервиса транзакций
 */
public class TransactionServiceImpl implements TransactionService {

    /** Внедрение зависимости репозитория транзакций */
    private final TransactionRepository transactionRepository;

    /** Конструктор класса */
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Response<Long> getTransactionByTransactionCode(Long transactionCode) {
        if(transactionCode == null){
            return new Response.Builder<Long>().missing(AppConstants.MISSING_TRANSACTION_CODE).build();
        }
        if(transactionRepository.isPresentTransactionByTransactionCode(transactionCode)){
            return new Response.Builder<Long>().alreadyExist(AppConstants.TRASACTION_CODE_ALREADY_EXISTS).build();
        }
        else return new Response.Builder<Long>().success(transactionCode).build();
    }

    /**
     * Получение всех транзакций банковского счета
     * @param id уникальный идентификатор банковского счета
     * @return список транзакций или описание ошибки
     */
    @Override
    public Response<List<Transaction>> getTransactionsByBankAccountId(Long id) {
        if(id == null){
            return new Response.Builder<List<Transaction>>().missing(AppConstants.MISSING_BANK_ACCOUNT_ID).build();
        }
        List<Transaction> transactionList = new ArrayList<>(transactionRepository.getTransactionsByBankAccountId(id));
        return new Response.Builder<List<Transaction>>().success(transactionList).build();
    }

    /**
     * Метод создания транзакции
     * @param person текущий пользователь
     * @param cash сумма транзакции
     * @param transactionCode уникальный идентификатор транзакции
     * @param transactionType тип транзакции
     * @return созданная транзакция или ошибка
     */
    @Override
    public Response<Transaction> createTransaction(Person person,
            Long cash, Long transactionCode, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCashValue(cash);
        transaction.setDate(LocalDateTime.now());
        transaction.setBankAccount(person.getBankAccount());
        transaction.setTransactionCode(transactionCode);
        Transaction createdTransaction = transactionRepository.createTransaction(transaction);
        if (createdTransaction.getId() == null){
            return new Response.Builder<Transaction>().failed(AppConstants.FAILED_TO_CREATE).build();
        }
        else return new Response.Builder<Transaction>().success(createdTransaction).build();
    }
}
