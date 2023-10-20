package service;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;
import com.furiosaming.walletService.repository.impl.AccountActionRepositoryImpl;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.AccountActionService;
import com.furiosaming.walletService.service.service.impl.AccountActionServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class AccountActionServiceTest {
    @Mock
    AccountActionRepositoryImpl accountActionRepositoryImpl;
    AccountActionService accountActionService;

    public AccountActionServiceTest(){
        MockitoAnnotations.openMocks(this);
        this.accountActionService = new AccountActionServiceImpl(accountActionRepositoryImpl);
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания действия в аккаунте" +
            "при невведенных пользователе и типе действия")
    void shouldNotCreateAccountActionByPersonAndActionType(){
        AccountAction accountAction = new AccountAction();
        Response<AccountAction> response = accountActionService.createAccountAction(accountAction);
        Assertions.assertEquals(response.getDescription(), AppConstants.MISSING_FIELDS);
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания действия в аккаунте из-за невведенного пользователя")
    void shouldNotCreateAccountActionByPerson(){
        AccountAction accountAction = new AccountAction();
        accountAction.setActionType(ActionType.LOG_IN);
        accountAction.setPerson(new Person());
        Response<AccountAction> response = accountActionService.createAccountAction(accountAction);
        Assertions.assertEquals(response.getDescription(), AppConstants.MISSING_FIELDS);
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания действия в аккаунте из-за невведенного типа действия")
    void shouldNotCreateAccountActionByActionType(){
        Person person = new Person();
        person.setId(1L);
        AccountAction accountAction = new AccountAction();
        accountAction.setPerson(person);
        Response<AccountAction> response = accountActionService.createAccountAction(accountAction);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }
}