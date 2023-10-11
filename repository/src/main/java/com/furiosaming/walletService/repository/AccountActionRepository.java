package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.AccountAction;

import java.util.List;

/**
 * Интерфейс репозитория действий с аккаунтом
 */
public interface AccountActionRepository {
    /**
     * Метод имитирует создание действия в аккаунте в базе данных
     * путем сохранения действия в список действий определенного пользователя
     * @param accountAction передает тип действия в аккаунте
     * @param accountActionList передает все существующие в памяти действия в аккаунте,
     *                         принадлежащие конкретному пользователю
     */
    void createAccountAction(AccountAction accountAction, List<AccountAction> accountActionList);
}
