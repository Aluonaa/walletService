package java.repository;

import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.enums.ActionType;
import com.furiosaming.walletService.repository.AccountActionRepository;
import com.furiosaming.walletService.repository.impl.AccountActionRepositoryImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;

public class AccountActionRepositoryTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:13.3-alpine"
    );

    AccountActionRepository  accountActionRepository = new AccountActionRepositoryImpl();

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    @DisplayName("Тест на успешное создание действия в аккаунте")
    void shouldCreateAccountAction(){
        AccountAction accountAction = new AccountAction();
        accountAction.setActionType(ActionType.LOG_IN);
        accountAction.setDate(LocalDateTime.now());
        Person person = new Person();
        person.setId(1L);
        accountAction.setPerson(person);
        accountAction = accountActionRepository.createAccountAction(accountAction);
        Assertions.assertNotNull(accountAction.getId());
    }
}
