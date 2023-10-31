package com.furiosaming.walletService.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    /** Уникальный идентификатор пользователя */
    private Long id;
    /** Паспортные данные пользователя */
    private String passport;
    /** Логин пользователя, является уникальным */
    private String login;
    /** Банковский счет, привязанный к аккаунту, создается вместе с пользователем */
    private BankAccountDto bankAccountDto;
}
