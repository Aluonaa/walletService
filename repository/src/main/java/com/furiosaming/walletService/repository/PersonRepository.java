package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.Person;

import java.util.List;

public interface PersonRepository {
    Person getPerson(String uuid, List<Person> personList);
    Person createPerson(Person person, List<Person> personList);
}
