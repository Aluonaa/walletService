package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.service.response.Response;

/**
 * Интерфейс сервиса пользователя
 */
public interface PersonService {
    /**
     * Метод авторизации пользователя
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return возврашает либо успешный статус и пользователя,
     * который становится текущим, либо описание ошибки
     */
    Response<Person> authorize(String login, String password);

    /**
     * Метод регистрации пользователя в программе
     * @param person пользователь, которого необходимо зарегистрировать
     * @return возврашает либо успешный статус и пользователя,
     * зарегистрированного в программе, либо описание ошибки
     */
    Response<Person> createPerson(Person person);

    /**
     * Метод поиска пользователя по логину, необходим для регистрации
     * и уникальности всех пользователей по логину
     * @param login логин пользователя
     * @return возвращает пользователя или null
     */
    Person getPersonByLogin(String login);
}