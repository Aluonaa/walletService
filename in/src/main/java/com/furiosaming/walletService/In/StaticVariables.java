package com.furiosaming.walletService.In;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.impl.AccountActionRepositoryImpl;
import com.furiosaming.walletService.repository.impl.BankAccountRepositoryImpl;
import com.furiosaming.walletService.repository.impl.PersonRepositoryImpl;
import com.furiosaming.walletService.repository.impl.TransactionRepositoryImpl;
import com.furiosaming.walletService.service.service.AccountActionService;
import com.furiosaming.walletService.service.service.BankAccountService;
import com.furiosaming.walletService.service.service.PersonService;
import com.furiosaming.walletService.service.service.impl.AccountActionServiceImpl;
import com.furiosaming.walletService.service.service.impl.BankAccountServiceImpl;
import com.furiosaming.walletService.service.service.impl.PersonServiceImpl;
import com.furiosaming.walletService.service.service.impl.TransactionServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Класс, созданный для хранения статических переменных,
 * которые необходимы для использования на протяжении всей работы программы
 */
public class StaticVariables {
    /** Переменная, хранящая текущего авторизованного пользователя */
    public static Person currentUser;
    /** Переменная для получения символов из потока */
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    /** Переменная сервиса пользователя*/
    public static PersonService personService = new PersonServiceImpl(new PersonRepositoryImpl(),
            new BankAccountServiceImpl(new BankAccountRepositoryImpl(), new TransactionServiceImpl(new TransactionRepositoryImpl())));
    /** Переменная сервиса банковского счета */
    public static BankAccountService bankAccountService =
            new BankAccountServiceImpl(new BankAccountRepositoryImpl(), new TransactionServiceImpl(new TransactionRepositoryImpl()));
    /** Переменная сервиса действий в аккаунте */
    public static AccountActionService accountActionService = new AccountActionServiceImpl(new AccountActionRepositoryImpl());
}
