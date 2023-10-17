package com.furiosaming.walletService.service.service.impl;


import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.BankAccountService;
import com.furiosaming.walletService.service.service.PersonService;

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
     * @return возврашает либо успешный статус и пользователя,
     * который становится текущим, либо описание ошибки
     */
    @Override
    public Response<Person> authorize(String login, String password) {
        Person person = personRepository.authorize(login, password);
        if (person != null) {
            return new Response.Builder<Person>().success(person).build();
        }
        else return new Response.Builder<Person>().notFound(AppConstants.INCORRECT_LOGIN_OR_PASSWORD).build();
    }

    /**
     * Метод регистрации пользователя в программе
     * @param person пользователь, которого необходимо зарегистрировать
     * @return возврашает либо успешный статус и пользователя,
     * зарегистрированного в программе, либо описание ошибки
     */
    @Override
    public Response<Person> createPerson(Person person) {
        if(person != null && person.getPassport() != null &&
                person.getLogin() != null && person.getPassword() != null){
            if(getPersonByLogin(person.getLogin()) != null){
                return new Response.Builder<Person>().alreadyExist(AppConstants.LOGIN_ALREADY_EXISTS).build();
            }
            Person createdPerson = personRepository.createPerson(person);
            if(createdPerson.getId() == null){
                return new Response.Builder<Person>().failed(AppConstants.FAILED_TO_CREATE).build();
            }
            Response<BankAccount> bankAccountResponse = bankAccountService.createBankAccount(createdPerson);
            if(!bankAccountResponse.getDescription().equals(AppConstants.SUCCESS)){
                return new Response.Builder<Person>().missing(bankAccountResponse.getDescription()).build();
            }
            createdPerson.setBankAccount(bankAccountResponse.getResult());
            return new Response.Builder<Person>().success(createdPerson).build();
        }
        else return new Response.Builder<Person>().missing(AppConstants.MISSING_FIELDS).build();
    }

    /**
     * Метод поиска пользователя по логину, необходим для регистрации
     * и уникальности всех пользователей по логину
     * @param login логин пользователя
     * @return возвращает пользователя или null
     */
    @Override
    public Person getPersonByLogin(String login) {
        return personRepository.findByLogin(login);
    }
}
