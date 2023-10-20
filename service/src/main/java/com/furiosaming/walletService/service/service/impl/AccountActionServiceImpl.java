package com.furiosaming.walletService.service.service.impl;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.repository.AccountActionRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.AccountActionService;


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
     * @param accountAction действие, которое необходимо создать
     * @return возвращает либо созданное действие, либо описание ошибки
     */
    @Override
    public Response<AccountAction> createAccountAction(AccountAction accountAction) {
        if (accountAction.getPerson() == null || accountAction.getPerson().getId() == null
                || accountAction.getActionType() == null) {
            return new Response.Builder<AccountAction>().missing(AppConstants.MISSING_FIELDS).build();
        }
        AccountAction createdAccountAction = accountActionRepository.createAccountAction(accountAction);
        if(createdAccountAction != null){
            accountAction.getPerson().getAccountActions().add(createdAccountAction);
            return new Response.Builder<AccountAction>().success(createdAccountAction).build();
        }
        else return new Response.Builder<AccountAction>().failed(AppConstants.FAILED_TO_CREATE).build();
    }
}
