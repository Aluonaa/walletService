package com.furiosaming.walletService.In.service;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.service.response.Response;

import java.io.IOException;
import java.util.List;

import static com.furiosaming.walletService.In.menu.SelectingMenuItem.chooseAuthorizedMenuItem;
import static com.furiosaming.walletService.In.help.InputErrorsCorrection.inputIdForCashInOut;
import static com.furiosaming.walletService.In.help.InputErrorsCorrection.inputSumForCashInOut;
import static com.furiosaming.walletService.In.menu.MenuVariants.displayAuthorizedActionsVariants;
import static com.furiosaming.walletService.In.StaticVariables.*;

/**
 * Класс, описывающий работу методов для авторизованных пользователей
 */
public class ActionsForAuthorized {
    /**
     * Метод, который позволяет вывести на экран баланс счета текущего пользователя
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void getBalance(List<Person> personList) throws IOException {
        double balance = currentUser.getBankAccount().getCashValue();
        System.out.println("Баланс счета " + balance + " руб.");
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem(personList);
    }

    /**
     * Метод, позволяющий получить историю транзакций текущего пользователя
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void getTransactionsHistory(List<Person> personList) throws IOException {
        List<Transaction> transactionsHistoryList = personService.getTransactionHistory(currentUser);
        for (Transaction transaction: transactionsHistoryList){
            System.out.println(transaction);
        }
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem(personList);
    }

    /**
     * Метод, позволяющий пополнить счет текущего пользователя
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void cashIn(List<Person> personList) throws IOException {
        Response<Double> result = bankAccountService.cashInOut(personList, currentUser,
                inputIdForCashInOut(personList), inputSumForCashInOut(personList), TransactionType.CASH_IN);
        System.out.println(result.getDescription());
        if(result.isStatus()){
            System.out.println("Текущий баланс счета: " + result.getResult() + " руб.");
        }
        else{
            cashIn(personList);
        }
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem(personList);
    }

    /**
     * Метод, позволяющий вывести средства со счета текущего пользователя
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void cashOut(List<Person> personList) throws IOException {
        Response<Double> result = bankAccountService.cashInOut(personList, currentUser,
                inputIdForCashInOut(personList), inputSumForCashInOut(personList), TransactionType.CASH_OUT);
        System.out.println(result.getDescription());
        if(result.isStatus()){
            System.out.println("Текущий баланс счета: " + result.getResult() + " руб.");
        }
        else{
            cashOut(personList);
        }
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem(personList);
    }
}
