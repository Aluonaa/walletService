package com.furiosaming.walletService.service.mapper;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.service.dto.PersonDto;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonDto personToDto(Person person);
    Person dtoToPerson(PersonDto personDto);
}
