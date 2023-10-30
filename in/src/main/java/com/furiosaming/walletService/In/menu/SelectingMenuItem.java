package com.furiosaming.walletService.In.menu;


import java.io.IOException;

import static com.furiosaming.walletService.In.service.AccountActions.*;
import static com.furiosaming.walletService.In.service.ActionsForAuthorized.*;


/**
 * Класс, содержащий методы, которые направляют пользователя при выборе пукта меню
 * в другие методы
 */
public class SelectingMenuItem {
//    /**
//     * Метод для перенаправления пользователя
//     * из стартового меню на регистрацию, авторизацию или выход из приложения
//     * @throws IOException исключения ввода-вывода
//     */
//    public static void chooseStartMenuItem() throws IOException {
//        int selectedMenuItem = 0;
//        selectedMenuItem = menuItemErrorsCorrection(selectedMenuItem);
//        if(selectedMenuItem == 1){
//            logIn();
//        }
//        else if(selectedMenuItem == 2){
//            registration();
//        }
//        else if(selectedMenuItem == 3){
//            System.out.println("До встречи!");
//        }
//        else{
//            System.out.println("Выбран несуществующий вариант, попробуйте еще раз.");
//            chooseStartMenuItem();
//        }
//    }
//
//    /**
//     * Метод для перенаправления пользователя
//     * из меню для авторизованных пользователей
//     * на методы пополнения счета, снятия денежных средств,
//     * вывод баланса, просмотр истории транзакций или на метод
//     * выхода из аккаунта
//     * @throws IOException исключения ввода-вывода
//     */
//    public static void chooseAuthorizedMenuItem() throws IOException {
//        int selectedMenuItem = 0;
//        selectedMenuItem = menuItemErrorsCorrection(selectedMenuItem);
//        if(selectedMenuItem == 1){
//            getBalance();
//        } else if(selectedMenuItem == 2 ){
//            cashInOut(TransactionType.CASH_IN);
//        } else if(selectedMenuItem == 3){
//            cashInOut(TransactionType.CASH_OUT);
//        } else if(selectedMenuItem == 4) {
//            getTransactionsHistory();
//        } else if(selectedMenuItem == 5) {
//            logOut();
//        } else{
//            System.out.println("Выбран несуществующий вариант, попробуйте еще раз.");
//            chooseAuthorizedMenuItem();
//        }
//    }
}