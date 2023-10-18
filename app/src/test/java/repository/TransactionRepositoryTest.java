package repository;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.TransactionRepository;
import com.furiosaming.walletService.repository.impl.TransactionRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    void shouldPresentByTransactionCode() {
        Long code = 1234567899876543L;
        Boolean result = transactionRepository.isPresentTransactionByTransactionCode(code);
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotPresentByTransactionCode() {
        Long code = 99912345678998L;
        Boolean result = transactionRepository.isPresentTransactionByTransactionCode(code);
        Assertions.assertFalse(result);
    }

    @Test
    void shouldGetTransactionsByBankAccountId() {
        Long id = 1L;
        List<Transaction> result = transactionRepository.getTransactionsByBankAccountId(id);
        Assertions.assertNotEquals(result.size(), 0);
    }

    @Test
    void shouldNotGetTransactionsByBankAccountId() {
        Long id = 112L;
        List<Transaction> result = transactionRepository.getTransactionsByBankAccountId(id);
        Assertions.assertSame(result.size(), 0);
    }
}
