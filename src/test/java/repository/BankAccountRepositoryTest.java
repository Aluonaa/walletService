package java.repository;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.BankAccountRepository;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.repository.impl.BankAccountRepositoryImpl;
import com.furiosaming.walletService.repository.impl.PersonRepositoryImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

public class BankAccountRepositoryTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:13.3-alpine"
    );
    BankAccountRepository bankAccountRepository = new BankAccountRepositoryImpl();
    PersonRepository personRepository = new PersonRepositoryImpl();

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    @DisplayName("Тест на успешное создание банковского счета")
    void shouldCreateBankAccount() {
        Person person = new Person();
        person.setPassword("password");
        person.setLogin("login");
        person.setPassport("passport");
        person = personRepository.createPerson(person);
        BankAccount bankAccount = bankAccountRepository.createBankAccount(person);
        Assertions.assertNotSame(bankAccount.getId(), 0L);
    }

    @Test
    @DisplayName("Тест на успешное получение банковского счета по id пользователя")
    void shouldGetBankAccountByPersonId(){
        Long id = 1L;
        BankAccount bankAccount = bankAccountRepository.getBankAccountByPersonId(id);
        Assertions.assertNotNull(bankAccount);
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения счета по id пользователя" +
            "при вводе несуществуюшего id пользователя")
    void shouldNotGetBankAccountByPersonId(){
        Long id = 8888888L;
        BankAccount bankAccount = bankAccountRepository.getBankAccountByPersonId(id);
        Assertions.assertNull(bankAccount);
    }

}
