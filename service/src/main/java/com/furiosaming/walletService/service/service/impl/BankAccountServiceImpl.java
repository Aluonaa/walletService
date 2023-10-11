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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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
     * Метод создания банковского счета
     * @param id уникальный идентификатор счета
     * @param personList список всех пользователей
     * @return возвращает созданный счет или описание ошибки
     */
    @Override
    public Response<BankAccount> createBankAccount(Long id, List<Person> personList) {
        if(id != null){
            BankAccount bankAccount = bankAccountRepository.createBankAccount(id);
            return new Response.Builder<BankAccount>().success(bankAccount).build();
        }
        else return new Response.Builder<BankAccount>().missing(AppConstants.MISSING_BANK_ACCOUNT_ID).build();
    }

    /**
     * Метод вывода средств и пополнения счета
     * @param personList список пользователей
     * @param person текущий пользователь, от лица которого совершается операция
     * @param transactionId уникальный идентификатор транзакции
     * @param cash сумма транзакции
     * @param transactionType тип транзакции
     * @return возвращает либо успешный статус операции и текущую сумму на счету,
     * либо описание ошибки
     */
    @Override
    public Response<Double> cashInOut(List<Person> personList, Person person,
                                      Long transactionId, Double cash, TransactionType transactionType) {
        if (cash<=0){
            return new Response.Builder<Double>().wrongData(AppConstants.INCORRECT_SUM).build();
        }
        Optional<Transaction> optional = personList.stream()
                .flatMap(p -> Stream.of(p.getBankAccount()))
                .flatMap(b -> Stream.of(b.getTransactions()))
                .flatMap(List::stream)
                .filter(transaction -> Objects.equals(transactionId, transaction.getId()))
                .findFirst();
        if(optional.isPresent()){
            return new Response.Builder<Double>().alreadyExist(AppConstants.ID_TRASACTION_ALREADY_EXISTS).build();
        }

        if(transactionType.equals(TransactionType.CASH_IN)){
            return cashIn(person, cash, transactionId, transactionType);
        }else {
            return cashOut(person, cash, transactionId, transactionType);
        }
    }

    /**
     * Метод, который создает непосредственно транзакцию пополнения счета
     * @param person текущий пользователь, от лица которого совершается операция
     * @param transactionId уникальный идентификатор транзакции
     * @param cash сумма транзакции
     * @param transactionType тип транзакции
     * @return возвращает либо текущуюсумму на счете, либо описание ошибки
     */
    public Response<Double> cashIn(Person person, Double cash, Long transactionId, TransactionType transactionType) {
        transactionService.createTransaction(person, cash, transactionId, transactionType);
        person.getBankAccount().setCashValue(person.getBankAccount().getCashValue() + cash);
        Double result = person.getBankAccount().getCashValue();
        return new Response.Builder<Double>().success(result).build();
    }

    /**
     * Метод, который создает непосредственно транзакцию вывода денежных средств
     * @param person текущий пользователь, от лица которого совершается операция
     * @param transactionId уникальный идентификатор транзакции
     * @param cash сумма транзакции
     * @param transactionType тип транзакции
     * @return возвращает либо текущуюсумму на счете, либо описание ошибки
     */
    public Response<Double> cashOut(Person person, Double cash, Long transactionId, TransactionType transactionType) {
        if(person.getBankAccount().getCashValue() < cash){
            return new Response.Builder<Double>().wrongData(AppConstants.INSUFFICIENT_FUNDS).build();
        }
        else{
            transactionService.createTransaction(person, cash, transactionId, transactionType);
            person.getBankAccount().setCashValue(person.getBankAccount().getCashValue() - cash);
            Double result = person.getBankAccount().getCashValue();
            return new Response.Builder<Double>().success(result).build();
        }
    }
}
