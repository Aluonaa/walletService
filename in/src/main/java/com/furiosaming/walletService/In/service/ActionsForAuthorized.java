package com.furiosaming.walletService.In.service;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.service.response.Response;

import java.io.IOException;
import java.util.List;

import static com.furiosaming.walletService.In.help.InputErrorsCorrection.inputCodeForCashInOut;
import static com.furiosaming.walletService.In.help.InputErrorsCorrection.inputSumForCashInOut;
import static com.furiosaming.walletService.In.menu.MenuVariants.displayAuthorizedActionsVariants;
import static com.furiosaming.walletService.In.StaticVariables.*;
import static com.furiosaming.walletService.In.menu.SelectingMenuItem.chooseAuthorizedMenuItem;

/**
 * Класс, описывающий работу методов для авторизованных пользователей
 */
public class ActionsForAuthorized {
    /**
     * Метод, который позволяет вывести на экран баланс счета текущего пользователя
     * @throws IOException исключения ввода-вывода
     */
    public static void getBalance() throws IOException {
        long balance = currentUser.getBankAccount().getCashValue();
        System.out.println("Баланс счета " + balance + " руб.");
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem();
    }

    /**
     * Метод, позволяющий получить историю транзакций текущего пользователя
     * @throws IOException исключения ввода-вывода
     */
    public static void  getTransactionsHistory() throws IOException {
        List<Transaction> transactionsHistoryList = transactionService.getTransactionsByBankAccountId(
                currentUser.getBankAccount().getId()).getResult();
        for (Transaction transaction: transactionsHistoryList){
            System.out.println(transaction);
        }
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem();
    }

    /**
     * Метод, позволяющий пополнить счет текущего пользователя
     * @throws IOException исключения ввода-вывода
     */
    public static void cashIn() throws IOException {
        Response<Long> result = bankAccountService.cashInOut( currentUser,
                inputCodeForCashInOut(), inputSumForCashInOut(), TransactionType.CASH_IN);
        System.out.println(result.getDescription());
        if(result.isStatus()){
            System.out.println("Текущий баланс счета: " + result.getResult() + " руб.");
        }
        else{
            cashIn();
        }
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem();
    }

    /**
     * Метод, позволяющий вывести средства со счета текущего пользователя
     * @throws IOException исключения ввода-вывода
     */
    public static void cashOut() throws IOException {
        Response<Long> result = bankAccountService.cashInOut(currentUser,
                inputCodeForCashInOut(), inputSumForCashInOut(), TransactionType.CASH_OUT);
        System.out.println(result.getDescription());
        if(result.isStatus()){
            System.out.println("Текущий баланс счета: " + result.getResult() + " руб.");
        }
        else{
            cashOut();
        }
        displayAuthorizedActionsVariants();
        chooseAuthorizedMenuItem();
    }
}
