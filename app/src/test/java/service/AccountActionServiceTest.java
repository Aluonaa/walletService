package service;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;
import com.furiosaming.walletService.repository.impl.AccountActionRepositoryImpl;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.AccountActionService;
import com.furiosaming.walletService.service.service.impl.AccountActionServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;


public class AccountActionServiceTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:13.3-alpine"
    );

    @Mock
    AccountActionRepositoryImpl accountActionRepositoryImpl;
    AccountActionService accountActionService;

    public AccountActionServiceTest(){
        MockitoAnnotations.openMocks(this);
        this.accountActionService = new AccountActionServiceImpl(accountActionRepositoryImpl);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    void shouldNotCreateAccountActionByPersonAndActionType(){
        Response<AccountAction> response = accountActionService.createAccountAction(null, null);
        Assertions.assertEquals(response.getDescription(), AppConstants.MISSING_FIELDS);
    }

    @Test
    void shouldNotCreateAccountActionByPerson(){
        Response<AccountAction> response = accountActionService.createAccountAction(null, ActionType.LOG_IN);
        Assertions.assertEquals(response.getDescription(), AppConstants.MISSING_FIELDS);
    }

    @Test
    void shouldNotCreateAccountActionByActionType(){
        Response<AccountAction> response = accountActionService.createAccountAction(new Person(
                1L, "1", "1", "1", new BankAccount(), new ArrayList<>()), null);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

//    @Test
//    void shouldCreateAccountAction(){
//        Person person = new Person(1L, "1", "1", "1", new BankAccount(), new ArrayList<>());
//        AccountAction accountActionSuccess = new AccountAction(1L, ActionType.LOG_IN, LocalDateTime.now(), person);
//        Mockito.when(accountActionRepositoryImpl.createAccountAction(accountActionSuccess)).thenReturn(
//                accountActionSuccess);
//        System.out.println(accountActionRepositoryImpl.createAccountAction(accountActionSuccess));
//        Response<AccountAction> response = accountActionService.createAccountAction(person, ActionType.LOG_IN);
//        System.out.println(response.getResult());
//        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
//    }


}
