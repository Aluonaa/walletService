package com.furiosaming.walletService.service.service.impl;


import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.BankAccountService;
import com.furiosaming.walletService.service.service.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * Имплементация интерфейса сервиса пользователя
 */
public class PersonServiceImpl implements PersonService {

    /** Внедрение зависимости репозитория пользователя */
    private final PersonRepository personRepository;
    /** Внедрение зависимости сервиса банковского счета */
    private final BankAccountService bankAccountService;

    /** Конструктор класса */
    public PersonServiceImpl(PersonRepository personRepository,
                             BankAccountService bankAccountService){
        this.personRepository = personRepository;
        this.bankAccountService = bankAccountService;
    }

    /**
     * Метод авторизации пользователя
     * @param login логин пользователя
     * @param password пароль пользователя
     * @param personList список всех пользователей
     * @return возврашает либо успешный статус и пользователя,
     * который становится текущим, либо описание ошибки
     */
    @Override
    public Response<Person> authorize(String login, String password, List<Person> personList) {
        Optional<Person> optionalPerson = personRepository.authorize(login, password, personList);
        if (optionalPerson.isPresent()) {
            return new Response.Builder<Person>().success(optionalPerson.get()).build();
        }
        else return new Response.Builder<Person>().notFound(AppConstants.INCORRECT_LOGIN_OR_PASSWORD).build();
    }

    /**
     * Метод регистрации пользователя в программе
     * @param person пользователь, которого необходимо зарегистрировать
     * @param bankAccountId уникальный идентификатор банковского счета пользователя
     * @param personList список всех пользователей
     * @return возврашает либо успешный статус и пользователя,
     * зарегистрированного в программе, либо описание ошибки
     */
    @Override
    public Response<Person> createPerson(Person person, Long bankAccountId, List<Person> personList) {
        if(person != null && person.getUuid() != null && person.getPassport() != null &&
                person.getLogin() != null && person.getPassword() != null){
            if(personList.stream().anyMatch(p -> p.getUuid().equals(person.getUuid()))){
                return new Response.Builder<Person>().alreadyExist(AppConstants.UUID_ALREADY_EXISTS).build();
            }
            if(personList.stream().anyMatch(p -> p.getLogin().equals(person.getLogin()))){
                return new Response.Builder<Person>().alreadyExist(AppConstants.LOGIN_ALREADY_EXISTS).build();
            }
            Person savedPerson = personRepository.createPerson(person, personList);
            Response<BankAccount> responseDto = bankAccountService.createBankAccount(bankAccountId, personList);
            if(!responseDto.getDescription().equals(AppConstants.SUCCESS)){
                return new Response.Builder<Person>().missing(responseDto.getDescription()).build();
            }
            else savedPerson.setBankAccount(responseDto.getResult());
            return new Response.Builder<Person>().success(savedPerson).build();
        }
        else return new Response.Builder<Person>().missing(AppConstants.MISSING_FIELDS).build();
    }
    /**
     * Метод получения истории транзакций пользователя
     * @param person текущий пользователь, желающий получить историю
     * @return список транзакций пользователя
     */
    @Override
    public List<Transaction> getTransactionHistory(Person person) {
        return person.getBankAccount().getTransactions();
    }
}
