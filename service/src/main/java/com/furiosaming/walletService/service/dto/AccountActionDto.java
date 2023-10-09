package com.furiosaming.walletService.service.dto;

import com.furiosaming.walletService.persistence.model.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountActionDto {
    private Long id;
    private ActionType actionType;
    private Date date;
    private BankAccountDto bankAccountDto;
}
