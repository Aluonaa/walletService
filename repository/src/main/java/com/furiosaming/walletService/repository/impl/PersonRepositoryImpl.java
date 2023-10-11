package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
/**
 * Имплементация интерфейса репозитория пользователя
 */
public class PersonRepositoryImpl implements PersonRepository {
    /**
     * Метод имитирует создание в базе данных пользователя
     * @param person пользователь, которого необходимо сохранить
     * @param personList список пользователей, имитирующий таблицу базы данных
     * @return возвращает созданного пользователя
     */
    public Person createPerson(Person person, List<Person> personList) {
        personList.add(person);
        return person;
    }
    /**
     * Метод авторизует пользователя
     * (проверяет в списке всех пользователей совпадения логина и пароля)
     * @param login логин пользователя
     * @param password пароль пользователя
     * @param personList список всех пользователей
     * @return возвращает либо найденного пользователя, либо null в
     *          безопасном типе Optional
     */
    @Override
    public Optional<Person> authorize(String login, String password, List<Person> personList) {
        return personList
                .stream()
                .filter(person -> login.equals(person.getLogin()))
                .filter(person -> password.equals(person.getPassword()))
                .findFirst();
    }
}
