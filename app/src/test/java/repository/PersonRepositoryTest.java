package repository;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.repository.impl.PersonRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    void shouldCreatePerson() {
        Person person = new Person();
        person.setPassword("password");
        person.setLogin("login");
        person.setPassport("passport");
        person = personRepository.createPerson(person);
        Assertions.assertNotNull(person.getId());
    }

    @Test
    void shouldFindByLogin(){
        String login = "login";
        Person person = personRepository.findByLogin(login);
        Assertions.assertNotNull(person);
    }

    @Test
    void shouldNotFindByLogin(){
        String login = "falseLogin";
        Person person = personRepository.findByLogin(login);
        Assertions.assertNull(person);
    }

    @Test
    void shouldNotAuthorizeByLoginAndPassword(){
        String login = "falseLogin";
        String password = "falsePassword";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNull(person);
    }

    @Test
    void shouldNotAuthorizeByLogin(){
        String login = "falseLogin";
        String password = "password";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNull(person);
    }

    @Test
    void shouldNotAuthorizeByPassword(){
        String login = "login";
        String password = "falsePassword";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNull(person);
    }

    @Test
    void shouldAuthorize(){
        String login = "login";
        String password = "password";
        Person person = personRepository.authorize(login, password);
        Assertions.assertNotNull(person);
    }
}
