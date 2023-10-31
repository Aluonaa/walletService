package com.furiosaming.walletService.service.mapper;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.service.dto.AccountActionDto;
import org.mapstruct.Mapper;

@Mapper
public interface AccountActionMapper {
    AccountActionDto accountActionToDto(AccountAction accountAction);
    AccountAction dtoToAccountAction(AccountActionDto accountActionDto);
}
