package service;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.BankAccountService;
import com.furiosaming.walletService.service.service.PersonService;
import com.furiosaming.walletService.service.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;

public class PersonServiceTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:13.3-alpine"
    );

    @Mock
    PersonRepository personRepository;
    @Mock
    BankAccountService bankAccountService;
    PersonService personService;

    public PersonServiceTest(){
        MockitoAnnotations.openMocks(this);
        this.personService = new PersonServiceImpl(personRepository, bankAccountService);
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
    void shouldAuthorize(){
        String login = "victorIvanov";
        String password = "12345";
        Person person = new Person(1L, "4400 567489", "victorIvanov", "12345", new BankAccount(), new ArrayList<>());
        Mockito.when(personRepository.authorize(login, password)).thenReturn(person);
        Response<Person> response = personService.authorize(login, password);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    void shouldNotAuthorize(){
        String login = "maxKlimov";
        String password = "8888";
        Mockito.when(personRepository.authorize(login, password)).thenReturn(null);
        Response<Person> response = personService.authorize(login, password);
        Assertions.assertEquals(AppConstants.INCORRECT_LOGIN_OR_PASSWORD, response.getDescription());
    }

    @Test
    void shouldNotCreate(){
        Person person = new Person();
        Response<Person> response = personService.createPerson(person);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    void shouldCreate(){
        Person person = new Person(1L, "1", "1", "1", new BankAccount(), new ArrayList<>());
        Mockito.when(personRepository.createPerson(person)).thenReturn(person);
        Mockito.when(bankAccountService.createBankAccount(person)).thenReturn(new Response.Builder<BankAccount>().success(new BankAccount()).build());
        Response<Person> response = personService.createPerson(person);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    void shouldNotCreateByLogin(){
        Person person = new Person(1L, "1", "1", "1", new BankAccount(), new ArrayList<>());
        Mockito.when(personRepository.findByLogin(person.getLogin())).thenReturn(person);
        Response<Person> response = personService.createPerson(person);
        Assertions.assertEquals(AppConstants.LOGIN_ALREADY_EXISTS, response.getDescription());
    }

}
