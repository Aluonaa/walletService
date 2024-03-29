package com.furiosaming.walletService.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /** Уникальный идентификатор пользователя */
    private Long id;
    /** Паспортные данные пользователя */
    private String passport;
    /** Логин пользователя, является уникальным */
    private String login;
    /** Пароль пользователя */
    private String password;
    /** Банковский счет, привязанный к аккаунту, создается вместе с пользователем */
    private BankAccount bankAccount;
    /** Список действий, связанных с аккаунтом пользователя */
    private List<AccountAction> accountActions = new ArrayList<>();
}
