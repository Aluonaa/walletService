package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.AccountAction;

/**
 * Интерфейс репозитория действий с аккаунтом
 */
public interface AccountActionRepository {
    /**
     * Метод создает действие в аккаунте в базе данных
     * @param accountAction передает действие в аккаунте
     */
    AccountAction createAccountAction(AccountAction accountAction);
}
