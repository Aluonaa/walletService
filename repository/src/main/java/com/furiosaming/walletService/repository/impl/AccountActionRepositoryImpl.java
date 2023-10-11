package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.repository.AccountActionRepository;

import java.util.List;

/**
 * Имплементация интерфейса репозитория действий в аккаунте
 */
public class AccountActionRepositoryImpl implements AccountActionRepository {
    /**
     * Метод имитирует создание действия в аккаунте в базе данных
     * путем сохранения действия в список действий определенного пользователя
     * @param accountAction передает тип действия в аккаунте
     * @param accountActionList передает все существующие в памяти действия в аккаунте,
     *                         принадлежащие конкретному пользователю
     */
    public void createAccountAction(AccountAction accountAction, List<AccountAction> accountActionList) {
        accountActionList.add(accountAction);
    }
}
