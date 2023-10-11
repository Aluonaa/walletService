package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.service.response.Response;

import java.util.List;

/**
 * Интерфейс сервиса пользователя
 */
public interface PersonService {
    /**
     * Метод авторизации пользователя
     * @param login логин пользователя
     * @param password пароль пользователя
     * @param personList список всех пользователей
     * @return возврашает либо успешный статус и пользователя,
     * который становится текущим, либо описание ошибки
     */
    Response<Person> authorize(String login, String password, List<Person> personList);

    /**
     * Метод регистрации пользователя в программе
     * @param person пользователь, которого необходимо зарегистрировать
     * @param bankAccountId уникальный идентификатор банковского счета пользователя
     * @param personList список всех пользователей
     * @return возврашает либо успешный статус и пользователя,
     * зарегистрированного в программе, либо описание ошибки
     */
    Response<Person> createPerson(Person person, Long bankAccountId, List<Person> personList);

    /**
     * Метод получения истории транзакций пользователя
     * @param person текущий пользователь, желающий получить историю
     * @return список транзакций пользователя
     */
    List<Transaction> getTransactionHistory(Person person);
}