package com.furiosaming.walletService.In.menu;

import com.furiosaming.walletService.persistence.model.Person;

import java.io.IOException;
import java.util.List;

import static com.furiosaming.walletService.In.help.InputErrorsCorrection.menuItemErrorsCorrection;
import static com.furiosaming.walletService.In.service.AccountActions.*;
import static com.furiosaming.walletService.In.service.ActionsForAuthorized.*;
import static com.furiosaming.walletService.In.service.ActionsForAuthorized.getTransactionsHistory;

/**
 * Класс, содержащий методы, которые направляют пользователя при выборе пукта меню
 * в другие методы
 */
public class SelectingMenuItem {
    /**
     * Метод для перенаправления пользователя
     * из стартового меню на регистрацию, авторизацию или выход из приложения
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void chooseStartMenuItem(List<Person> personList) throws IOException {
        int selectedMenuItem = 0;
        selectedMenuItem = menuItemErrorsCorrection(selectedMenuItem, personList);
        if(selectedMenuItem == 1){
            logIn(personList);
        }
        else if(selectedMenuItem == 2){
            registration(personList);
        }
        else if(selectedMenuItem == 3){
            System.out.println("До встречи!");
        }
        else{
            System.out.println("Выбран несуществующий вариант, попробуйте еще раз.");
            chooseStartMenuItem(personList);
        }
    }

    /**
     * Метод для перенаправления пользователя
     * из меню для авторизованных пользователей
     * на методы пополнения счета, снятия денежных средств,
     * вывод баланса, просмотр истории транзакций или на метод
     * выхода из аккаунта
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void chooseAuthorizedMenuItem(List<Person> personList) throws IOException {
        int selectedMenuItem = 0;
        selectedMenuItem = menuItemErrorsCorrection(selectedMenuItem, personList);
        if(selectedMenuItem == 1){
            getBalance(personList);
        }
        else if(selectedMenuItem == 2){
            cashIn(personList);
        }
        else if(selectedMenuItem == 3){
            cashOut(personList);
        }
        else if(selectedMenuItem == 4) {
            getTransactionsHistory(personList);
        } else if(selectedMenuItem == 5) {
            logOut(personList);
        }
        else{
            System.out.println("Выбран несуществующий вариант, попробуйте еще раз.");
            chooseAuthorizedMenuItem(personList);
        }
    }
}
