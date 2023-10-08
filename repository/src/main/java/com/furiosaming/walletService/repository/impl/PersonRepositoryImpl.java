package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;

import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    public Person getPerson(String uuid, List<Person> personList) {
         for(Person person: personList){
             if(person.getUuid().equals(uuid)){
                 return person;
             }
         }
         return null;
    }

    public Person createPerson(Person person, List<Person> personList) {
        personList.add(person);
        return person;
    }
}
