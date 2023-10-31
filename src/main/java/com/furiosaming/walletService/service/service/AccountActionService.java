package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.service.response.Response;

/**
 * Интерфейс сервиса действий с аккаунтом
 */
public interface AccountActionService {
    /**
     * Метод создания действия с аккаунтом
     * @param accountAction действие, которое необходимо создать
     * @return созданное действие в аккаунте или описание ошибки
     */
    Response<AccountAction> createAccountAction(AccountAction accountAction);
}

