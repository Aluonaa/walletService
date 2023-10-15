package com.furiosaming.walletService.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, описывающий пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /** Электронный адрес пользователя, является уникальным */
    private String uuid;
    /** Паспортные данные пользователя */
    private String passport;
    /** Логин пользователя, является уникальным */
    private String login;
    /** Пароль пользователя */
    private String password;
    /** Банковский счет, привязанный к аккаунту, создается вместе с пользователем */
    private BankAccount bankAccount;
}
