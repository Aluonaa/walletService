package com.furiosaming.walletService.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий банковский счет клиента
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    /** Уникальный идентификатор банковского счета */
    private Long id;
    /** Сумма, которая находится на счету в данный момент */
    private long cashValue;
    /** Список транзакций, проводившихся на данном счете */
    private List<Transaction> transactions = new ArrayList<>();
    /** Список действий, связанных с аккаунтом пользователя */
    private List<AccountAction> accountActions = new ArrayList<>();
}
