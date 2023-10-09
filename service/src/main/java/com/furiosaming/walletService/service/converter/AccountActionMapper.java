package com.furiosaming.walletService.service.converter;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.service.dto.AccountActionDto;

public class AccountActionMapper {

    public static AccountActionDto accountActionToDtoMap(AccountAction accountAction){
        AccountActionDto accountActionDto = new AccountActionDto();
        accountActionDto.setId(accountAction.getId());
        accountActionDto.setActionType(accountAction.getActionType());
        accountActionDto.setDate(accountAction.getDate());
        accountActionDto.setBankAccountDto(BankAccountMapper.bankAccountToDtoMap(accountAction.getBankAccount()));
        return accountActionDto;
    }

    public static AccountAction dtoToAccountActionMap(AccountActionDto accountActionDto){
        AccountAction accountAction = new AccountAction();
        accountAction.setId(accountActionDto.getId());
        accountAction.setActionType(accountActionDto.getActionType());
        accountAction.setDate(accountActionDto.getDate());
        accountAction.setBankAccount(BankAccountMapper.dtoToBankAccountMap(accountActionDto.getBankAccountDto()));
        return accountAction;
    }
}
