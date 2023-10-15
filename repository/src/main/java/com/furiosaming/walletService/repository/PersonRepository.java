package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория пользователя
 */
public interface PersonRepository {
    /**
     * Метод имитирует создание в базе данных пользователя
     * @param person пользователь, которого необходимо сохранить
     * @param personList список пользователей, имитирующий таблицу базы данных
     * @return возвращает созданного пользователя
     */
    Person createPerson(Person person, List<Person> personList);

    /**
     * Метод авторизует пользователя
     * (проверяет в списке всех пользователей совпадения логина и пароля)
     * @param login логин пользователя
     * @param password пароль пользователя
     * @param personList список всех пользователей
     * @return возвращает либо найденного пользователя, либо null в
     *          безопасном типе Optional
     */
    Optional<Person> authorize(String login, String password, List<Person> personList);

}

