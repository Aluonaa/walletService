package repository;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.repository.impl.PersonRepositoryImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

public class PersonRepositoryTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:13.3-alpine"
    );
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
    @DisplayName("Тест на успешное создание пользователя")
    void shouldCreatePerson() {
        Person person = new Person();
        person.setPassword("password");
        person.setLogin("login");
        person.setPassport("passport");
        person = personRepository.createPerson(person);
        Assertions.assertNotNull(person.getId());
    }

    @Test
    @DisplayName("Тест на успешный поиск пользователя по логину")
    void shouldFindByLogin(){
        String login = "login";
        Person person = personRepository.findByLogin(login);
        Assertions.assertNotNull(person);
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска пользователя по логину" +
            "при вводе несуществующего логина")
    void shouldNotFindByLogin(){
        String login = "falseLogin";
        Person person = personRepository.findByLogin(login);
        Assertions.assertNull(person);
    }

    @Test
    @DisplayName("Тест на неудачную попытку авторизации при вводе неверных" +
            "логина и пароля")
    void shouldNotAuthorizeByLoginAndPassword(){
        String login = "falseLogin";
        String password = "falsePassword";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNull(person);
    }

    @Test
    @DisplayName("Тест на неудачную попытку авторизации при вводе неверного логина")
    void shouldNotAuthorizeByLogin(){
        String login = "falseLogin";
        String password = "password";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNull(person);
    }

    @Test
    @DisplayName("Тест на неудачную попытку авторизации при вводе неверного пароля")
    void shouldNotAuthorizeByPassword(){
        String login = "login";
        String password = "falsePassword";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNull(person);
    }

    @Test
    @DisplayName("Тест на успешную авторизацию")
    void shouldAuthorize(){
        String login = "login";
        String password = "password";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNotNull(person);
    }
}
