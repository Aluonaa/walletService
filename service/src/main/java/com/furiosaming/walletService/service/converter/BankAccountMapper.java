package com.furiosaming.walletService.service.converter;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.service.dto.BankAccountDto;

public class BankAccountMapper {
    public static BankAccountDto bankAccountToDtoMap(BankAccount bankAccount){
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setId(bankAccount.getId());
        bankAccountDto.setCashValue(bankAccount.getCashValue());
        bankAccountDto.setPerson(PersonMapper.personToDtoMap(bankAccount.getPerson()));
        return bankAccountDto;
    }

    public static BankAccount dtoToBankAccountMap(BankAccountDto bankAccountDto){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountDto.getId());
        bankAccount.setCashValue(bankAccountDto.getCashValue());
        bankAccount.setPerson(PersonMapper.dtoToPersonMap(bankAccountDto.getPerson()));
        return bankAccount;
    }
}
