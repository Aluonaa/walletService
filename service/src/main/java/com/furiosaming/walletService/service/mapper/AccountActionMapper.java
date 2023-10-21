package com.furiosaming.walletService.service.mapper;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.service.dto.AccountActionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountActionMapper {
    AccountActionMapper INSTANCE = Mappers.getMapper( AccountActionMapper.class );

    AccountActionDto accountActionToDto(AccountAction accountAction);
    AccountAction dtoToAccountAction(AccountActionDto accountActionDto);
}
