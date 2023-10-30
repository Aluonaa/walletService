package com.furiosaming.walletService.In.service;

import com.furiosaming.walletService.In.StaticVariables;

import java.io.IOException;

import static com.furiosaming.walletService.In.menu.Menu.start;
import static com.furiosaming.walletService.In.menu.MenuVariants.displayAuthorizedActionsVariants;
import static com.furiosaming.walletService.In.StaticVariables.*;

/**
 * Класс, созданный для методов, которые доступны авторизованным пользователям
 */
public class AccountActions {
//    /**
//     * Метод, позволяющий пользователю пройти регистрацию в приложении
//     * @throws IOException исключения ввода-вывода
//     */
//    public static void registration() throws IOException {
//        Person person = enteringRegistrationData();
//
//        Response<Person> response = personService.createPerson(person);
//        if(!response.isStatus()){
//            registration();
//        }
//        else{
//            AccountAction accountAction = new AccountAction();
//            accountAction.setPerson(response.getResult());
//            accountAction.setActionType(ActionType.REGISTRATION);
//            accountActionService.createAccountAction(accountAction);
//            start();
//        }
//    }
//
//    /**
//     * Метод ввода данных для регистрации
//     * @return возвращает пользователя с заполненными полями
//     * @throws IOException исключения ввода-вывода
//     */
//    public static Person  enteringRegistrationData() throws IOException {
//        Person person = new Person();
//        System.out.println("Введите данные паспорта:");
//        person.setPassport(in.readLine());
//        System.out.println("Введите логин:");
//        person.setLogin(in.readLine());
//        System.out.println("Введите пароль:");
//        person.setPassword(in.readLine());
//        return person;
//    }
//
//    /**
//     * Метод для авторизации в приложении, проверка через логин и пароль
//     * @throws IOException исключения ввода-вывода
//     */
//    public static void logIn() throws IOException {
//        System.out.println("Введите логин:");
//        String login = in.readLine();
//        System.out.println("Введите пароль:");
//        String password = in.readLine();
//        Response<Person> response = personService.authorize(login, password);
//        if(!response.getDescription().equals(AppConstants.SUCCESS)){
//            start();
//        }
//        else {
//            currentUser = response.getResult();
//            Response<BankAccount> bankAccountResponse = bankAccountService.getBankAccountByPersonId(currentUser.getId());
//            if(!bankAccountResponse.isStatus()){
//                System.out.println(bankAccountResponse.getDescription());
//                start();
//            }
//            currentUser.setBankAccount(bankAccountResponse.getResult());
//            AccountAction accountAction = new AccountAction();
//            accountAction.setPerson(currentUser);
//            accountAction.setActionType(ActionType.LOG_IN);
//            accountActionService.createAccountAction(accountAction);
//            displayAuthorizedActionsVariants();
//            chooseAuthorizedMenuItem();
//        }
//    }
//
//    /**
//     * Метод выхода из аккаунта
//     * @throws IOException исключения ввода-вывода
//     */
//    public static void logOut() throws IOException {
//        AccountAction accountAction = new AccountAction();
//        accountAction.setPerson(currentUser);
//        accountAction.setActionType(ActionType.LOG_OUT);
//        accountActionService.createAccountAction(accountAction);
//        StaticVariables.currentUser = null;
//        start();
//    }
}