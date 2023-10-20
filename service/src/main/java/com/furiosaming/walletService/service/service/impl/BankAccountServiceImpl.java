package com.furiosaming.walletService.service.service.impl;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.BankAccountRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.BankAccountService;
import com.furiosaming.walletService.service.service.TransactionService;


/**
 * Имплементация интерфейса сервиса банковского счета
 */
public class BankAccountServiceImpl implements BankAccountService {

    /** Внедрение зависимости репозитория банковского счета */
    private final BankAccountRepository bankAccountRepository;
    /** Внедрение зависимости сервиса транзакций */
    private final TransactionService transactionService;

    /** Конструктор класса */
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  TransactionService transactionService){
        this.bankAccountRepository = bankAccountRepository;
        this.transactionService = transactionService;
    }

    /**
     * Находит банковский счет по id
     * @param id уникальный идентификатор пользователя
     * @return банковский счет или описание ошибки
     */
    public Response<BankAccount> getBankAccountByPersonId(Long id){
        if(id == null){
            return new Response.Builder<BankAccount>().missing(AppConstants.MISSING_PERSON_ID).build();
        }
        BankAccount bankAccount = bankAccountRepository.getBankAccountByPersonId(id);
        if(bankAccount == null){
            return new Response.Builder<BankAccount>().notFound(AppConstants.BANK_ACCOUNT_NOT_FOUND).build();
        }
        else return new Response.Builder<BankAccount>().success(bankAccount).build();
    }

    /**
     * Метод создания банковского счета
     * @param person владелец аккаунта
     * @return возвращает созданный счет или описание ошибки
     */
    @Override
    public Response<BankAccount> createBankAccount(Person person) {
        if(person != null && person.getId() != null){
            BankAccount bankAccount = bankAccountRepository.createBankAccount(person);
            if(bankAccount == null){
                return new Response.Builder<BankAccount>().failed(AppConstants.FAILED_TO_CREATE).build();
            }
            else return new Response.Builder<BankAccount>().success(bankAccount).build();
        }
        else return new Response.Builder<BankAccount>().missing(AppConstants.MISSING_FIELDS).build();
    }



    /**
     * Метод вывода средств и пополнения счета
     * @param transaction транзакция, которую необходимо провести
     * @return возвращает либо успешный статус операции и текущую сумму на счету,
     * либо описание ошибки
     */
    @Override
    public Response<Long> cashInOut(Transaction transaction) {
        Response<Long> transactionResponse = transactionService.getTransactionByTransactionCode(transaction.getTransactionCode());
        if(transactionResponse.getDescription().equals(AppConstants.TRASACTION_CODE_ALREADY_EXISTS)){
            return new Response.Builder<Long>().alreadyExist(AppConstants.TRASACTION_CODE_ALREADY_EXISTS).build();
        }
        if(transaction.getTransactionType().equals(TransactionType.CASH_IN)){
            return cashIn(transaction);
        }else {
            return cashOut(transaction);
        }
    }

    /**
     * Метод, который создает непосредственно транзакцию пополнения счета
     * @param transaction транзакция, которую необходимо провести
     * @return возвращает либо текущуюсумму на счете, либо описание ошибки
     */
    public Response<Long> cashIn(Transaction transaction) {
        Response<Transaction> transactionResponse = transactionService.createTransaction(transaction);
        if(transactionResponse.isStatus()){
            transaction.getBankAccount().setCashValue(transaction.getBankAccount().getCashValue() + transaction.getCashValue());
            Long result = transaction.getBankAccount().getCashValue();
            return new Response.Builder<Long>().success(result).build();
        }
        return new Response.Builder<Long>().failed(AppConstants.FAILED_TO_CASH_IN).build();
    }


    /**
     * Метод, который создает непосредственно транзакцию вывода денежных средств
     * @param transaction транзакция, которую необходимо провести
     * @return возвращает либо текущуюсумму на счете, либо описание ошибки
     */
    public Response<Long> cashOut(Transaction transaction) {
        if(transaction.getBankAccount().getCashValue() < transaction.getCashValue()){
            return new Response.Builder<Long>().wrongData(AppConstants.INSUFFICIENT_FUNDS).build();
        }
        Response<Transaction> transactionResponse = transactionService.createTransaction(transaction);
        if(transactionResponse.isStatus()){
            transaction.getBankAccount().setCashValue(transaction.getBankAccount().getCashValue() - transaction.getCashValue());
            Long result = transaction.getBankAccount().getCashValue();
            return new Response.Builder<Long>().success(result).build();
        }
        return new Response.Builder<Long>().failed(AppConstants.FAILED_TO_CASH_IN).build();
    }
}
