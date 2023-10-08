package com.furiosaming.walletService.repository;

import com.furiosaming.walletService.persistence.model.AccountAction;

import java.util.List;

public interface AccountActionRepository {
    AccountAction createAccountAction(AccountAction accountAction, List<AccountAction> accountActionList);
}
