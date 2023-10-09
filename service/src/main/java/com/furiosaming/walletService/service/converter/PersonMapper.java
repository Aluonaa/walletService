package com.furiosaming.walletService.service.converter;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.service.dto.PersonDto;

public class PersonMapper {
    public static PersonDto personToDtoMap(Person person){
        PersonDto personDto = new PersonDto();
        personDto.setUuid(person.getUuid());
        personDto.setPassport(person.getPassport());
        personDto.setLogin(person.getLogin());
        personDto.setPassword(person.getPassword());
        return personDto;
    }

    public static Person dtoToPersonMap(PersonDto personDto){
        Person person = new Person();
        person.setUuid(personDto.getUuid());
        person.setPassport(personDto.getPassport());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        return person;
    }
}
