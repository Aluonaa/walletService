package com.furiosaming.walletService.service.service.impl;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;
import com.furiosaming.walletService.repository.AccountActionRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.AccountActionService;

import java.time.LocalDateTime;

/**
 * Имплементация интерфейса сервиса действий с аккаунтом
 */
public class AccountActionServiceImpl implements AccountActionService {
    /** Внедрение зависимости репозитория действий с аккаунтом */
    private final AccountActionRepository accountActionRepository;

    /**
     * Конструктор класса
     * @param accountActionRepository репозиторий действий с аккаунтом
     */
    public AccountActionServiceImpl(AccountActionRepository accountActionRepository){
        this.accountActionRepository = accountActionRepository;
    }
    /**
     * Метод создания действия с аккаунтом
     * @param person аккаунт (пользователь), в котором совешается действие
     * @param actionType тип действия
     * @return возвращает либо созданное действие, либо описание ошибки
     */
    @Override
    public Response<AccountAction> createAccountAction(Person person, ActionType actionType) {
        AccountAction accountAction = new AccountAction();
        accountAction.setActionType(actionType);
        accountAction.setDate(LocalDateTime.now());
        accountAction.setPerson(person);
        AccountAction createdAccountAction = accountActionRepository.createAccountAction(accountAction);
        if(createdAccountAction != null){
            person.getAccountActions().add(createdAccountAction);
            return new Response.Builder<AccountAction>().success(createdAccountAction).build();
        }
        else return new Response.Builder<AccountAction>().failed(AppConstants.FAILED_TO_CREATE).build();
    }
}
