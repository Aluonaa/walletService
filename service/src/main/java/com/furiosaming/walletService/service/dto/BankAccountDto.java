package com.furiosaming.walletService.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    /** Уникальный идентификатор банковского счета */
    private Long id;
    /** Сумма, которая находится на счету в данный момент */
    private long cashValue;
    /** Пользователь, владелец счета */
    private PersonDto personDto;
}
