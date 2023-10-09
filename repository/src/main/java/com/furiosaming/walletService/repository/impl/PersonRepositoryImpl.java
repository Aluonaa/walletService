package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    public Person getPerson(String uuid, List<Person> personList) {
        Optional<Person> result = personList.stream().filter(person -> uuid.equals(person.getUuid())).findFirst();
        return result.orElse(null);
    }

    public Person createPerson(Person person, List<Person> personList) {
        personList.add(person);
        return person;
    }

    @Override
    public Person authorize(String login, String password, List<Person> personList) {
        Optional<Person> result = personList.stream().filter(person -> login.equals(person.getLogin())).findFirst();
        if (result.isPresent()) {
            result = personList.stream().filter(person -> password.equals(person.getPassword())).findFirst();
        }
        return result.orElse(null);
    }
}
