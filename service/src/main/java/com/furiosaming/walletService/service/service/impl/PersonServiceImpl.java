package com.furiosaming.walletService.service.service.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.service.dto.PersonDto;
import com.furiosaming.walletService.service.response.BaseResponseDto;
import com.furiosaming.walletService.service.service.PersonService;

import java.util.List;


public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

}
