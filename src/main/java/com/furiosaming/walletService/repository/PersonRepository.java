package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.Person;

/**
 * Интерфейс репозитория пользователя
 */
public interface PersonRepository {

    /**
     * Метод поиска пользователя по логину, необходим для регистрации
     * и уникальности всех пользователей по логину
     * @param login логин пользователя
     * @return возвращает найденного пользователя, либо null
     */
    Person findByLogin(String login);

    /**
     * Метод создает в базе данных пользователя
     * @param person пользователь, которого необходимо сохранить
     * @return возвращает созданного пользователя
     */
    Person createPerson(Person person);

    /**
     * Метод авторизует пользователя
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return возвращает либо найденного пользователя, либо null
     */
    Person authorize(String login, String password);

}

