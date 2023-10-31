package java.repository;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.TransactionRepository;
import com.furiosaming.walletService.repository.impl.TransactionRepositoryImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionRepositoryTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:13.3-alpine"
    );
    TransactionRepository transactionRepository = new TransactionRepositoryImpl();

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
    void shouldCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(1234567899876543L);
        transaction.setTransactionType(TransactionType.CASH_IN);
        transaction.setCashValue(100L);
        transaction.setDate(LocalDateTime.now());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        transaction.setBankAccount(bankAccount);
        transaction = transactionRepository.createTransaction(transaction);
        Assertions.assertNotNull(transaction.getId());
    }

    @Test
    @DisplayName("Тест на успешную проверку существования транзакции по коду" +
            "(транзакция с таким кодом уже существует в базе)")
    void shouldPresentByTransactionCode() {
        Long code = 1234567899876543L;
        Boolean result = transactionRepository.isPresentTransactionByTransactionCode(code);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска транзакции по коду " +
            "(транзакции с таким кодом не существует в базе)")
    void shouldNotPresentByTransactionCode() {
        Long code = 99912345678998L;
        Boolean result = transactionRepository.isPresentTransactionByTransactionCode(code);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Тест на успешное получение списка транзакций по id счета")
    void shouldGetTransactionsByBankAccountId() {
        Long id = 1L;
        List<Transaction> result = transactionRepository.getTransactionsByBankAccountId(id);
        Assertions.assertNotEquals(result.size(), 0);
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения транзакции по id счета (введен несуществующий id)")
    void shouldNotGetTransactionsByBankAccountId() {
        Long id = 112L;
        List<Transaction> result = transactionRepository.getTransactionsByBankAccountId(id);
        Assertions.assertSame(result.size(), 0);
    }
}
