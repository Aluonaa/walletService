package com.furiosaming.walletService.service.mapper;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.service.dto.BankAccountDto;
import org.mapstruct.Mapper;

@Mapper
public interface BankAccountMapper {
    BankAccountDto bankAccountToDto(BankAccount bankAccount);
    BankAccount dtoToBankAccount(BankAccountDto bankAccountDto);
}
