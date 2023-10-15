package com.furiosaming.walletService.In.service;

import com.furiosaming.walletService.In.StaticVariables;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.PersonService;

import java.io.IOException;
import java.util.List;

import static com.furiosaming.walletService.In.help.InputErrorsCorrection.enteringBankAccountId;
import static com.furiosaming.walletService.In.menu.Menu.start;
import static com.furiosaming.walletService.In.menu.MenuVariants.displayAuthorizedActionsVariants;
import static com.furiosaming.walletService.In.StaticVariables.*;
import static com.furiosaming.walletService.In.menu.SelectingMenuItem.chooseAuthorizedMenuItem;

/**
 * Класс, созданный для методов, которые доступны авторизованным пользователям
 */
public class AccountActions {
    /**
     * Метод, позволяющий пользователю пройти регистрацию в приложении
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void registration(List<Person> personList) throws IOException {
        Person person = enteringRegistrationData();
        long bankAccountId = enteringBankAccountId();

        PersonService personService = StaticVariables.personService;

        Response<Person> response = personService.createPerson(person, bankAccountId, personList);
        System.out.println(response.getDescription());
        if(!response.isStatus()){
            registration(personList);
        }
        else{
            accountActionService.createAccountAction(response.getResult(), ActionType.REGISTRATION);
            start(personList);
        }
    }

    /**
     * Метод ввода данных для регистрации
     * @return возвращает пользователя с заполненными полями
     * @throws IOException исключения ввода-вывода
     */
    public static Person enteringRegistrationData() throws IOException {
        Person person = new Person();
        System.out.println("Введите адрес электронной почты:");
        person.setUuid(in.readLine());
        System.out.println("Введите данные паспорта:");
        person.setPassport(in.readLine());
        System.out.println("Введите логин:");
        person.setLogin(in.readLine());
        System.out.println("Введите пароль:");
        person.setPassword(in.readLine());
        return person;
    }

    /**
     * Метод для авторизации в приложении, проверка через логин и пароль
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void logIn(List<Person> personList) throws IOException {
        PersonService personService = StaticVariables.personService;
        System.out.println("Введите логин:");
        String login = in.readLine();
        System.out.println("Введите пароль:");
        String password = in.readLine();
        Response<Person> response = personService.authorize(login, password, personList);
        System.out.println(response.getDescription());
        if(!response.getDescription().equals(AppConstants.SUCCESS)){
            start(personList);
        }
        else {
            StaticVariables.currentUser = response.getResult();
            accountActionService.createAccountAction(currentUser, ActionType.LOG_IN);
            displayAuthorizedActionsVariants();
            chooseAuthorizedMenuItem(personList);
        }
    }

    /**
     * Метод выхода из аккаунта
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void logOut(List<Person> personList) throws IOException {
        accountActionService.createAccountAction(currentUser, ActionType.LOG_OUT);
        StaticVariables.currentUser = null;
        start(personList);
    }
}
