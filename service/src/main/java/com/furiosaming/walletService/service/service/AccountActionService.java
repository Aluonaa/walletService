package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;
import com.furiosaming.walletService.service.response.Response;

/**
 * Интерфейс сервиса действий с аккаунтом
 */
public interface AccountActionService {
    /**
     * Метод создания действия с аккаунтом
     * @param person аккаунт (пользователь), в котором совешается действие
     * @param actionType тип действия
     * @return созданное действие в аккаунте или описание ошибки
     */
    Response<AccountAction> createAccountAction(Person person, ActionType actionType);
}
