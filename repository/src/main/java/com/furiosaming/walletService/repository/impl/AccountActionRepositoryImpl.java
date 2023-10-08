package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.repository.AccountActionRepository;

import java.util.List;

public class AccountActionRepositoryImpl implements AccountActionRepository {

    public AccountAction createAccountAction(AccountAction accountAction, List<AccountAction> accountActionList) {
        accountActionList.add(accountAction);
        return accountAction;
    }
}
