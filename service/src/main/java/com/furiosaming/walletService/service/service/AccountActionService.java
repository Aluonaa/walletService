package com.furiosaming.walletService.service.service;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;

/**
 * Интерфейс сервиса действий с аккаунтом
 */
public interface AccountActionService {
    /**
     * Метод создания действия с аккаунтом
     * @param person аккаунт (пользователь), в котором совешается действие
     * @param actionType тип действия
     */
    void createAccountAction(Person person, ActionType actionType);
}
