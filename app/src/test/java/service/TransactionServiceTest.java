package service;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.impl.TransactionRepositoryImpl;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.TransactionService;
import com.furiosaming.walletService.service.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceTest {
    TransactionService transactionService;
    @Mock
    TransactionRepositoryImpl transactionRepositoryImpl;

    public TransactionServiceTest(){
        MockitoAnnotations.openMocks(this);
        this.transactionService = new TransactionServiceImpl(transactionRepositoryImpl);
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска транзакции по коду " +
            "(транзакции с таким кодом не существует в базе)")
    void shouldNotGetTransactionByTransactionCode(){
        Long transactionCode = 1L;
        Mockito.when(transactionRepositoryImpl.isPresentTransactionByTransactionCode(transactionCode)).thenReturn(false);
        Response<Long> response = transactionService.getTransactionByTransactionCode(transactionCode);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на успешную проверку существования транзакции по коду" +
            "(транзакция с таким кодом уже существует в базе)")
    void shouldGetTransactionByTransactionCode(){
        Long transactionCode = 1L;
        Mockito.when(transactionRepositoryImpl.isPresentTransactionByTransactionCode(transactionCode)).thenReturn(true);
        Response<Long> response = transactionService.getTransactionByTransactionCode(transactionCode);
        Assertions.assertEquals(AppConstants.TRASACTION_CODE_ALREADY_EXISTS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска транзакции по коду " +
            "из-за невведенного кода транзакции")
    void shouldNotGetTransactionByNullTransactionCode(){
        Long transactionCode = null;
        Response<Long> response = transactionService.getTransactionByTransactionCode(transactionCode);
        Assertions.assertEquals(AppConstants.MISSING_TRANSACTION_CODE, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку получения транзакции по id счета (не введен id)")
    void shouldNotGetTransactionsByBankAccountIdByNull(){
        Long id = null;
        Response<List<Transaction>> response = transactionService.getTransactionsByBankAccountId(id);
        Assertions.assertEquals(AppConstants.MISSING_BANK_ACCOUNT_ID, response.getDescription());
    }

    @Test
    @DisplayName("Тест на успешное получение транзакции по id счета")
    void shouldGetTransactionsByBankAccountId(){
        Long id = 1L;
        Mockito.when(transactionRepositoryImpl.getTransactionsByBankAccountId(id)).thenReturn(new ArrayList<>());
        Response<List<Transaction>> response = transactionService.getTransactionsByBankAccountId(id);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания транзакции из-за незаполненных полей")
    void shouldNotCreateTransaction(){
        Response<Transaction> response = transactionService.createTransaction(
                new Transaction());
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания транзакции из-за незаполненного пользователя")
    void shouldNotCreateTransactionByPersonId(){
        Person person = new Person();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCashValue(0L);
        person.setBankAccount(bankAccount);
        Transaction transaction = new Transaction();
        transaction.setBankAccount(person.getBankAccount());
        transaction.setTransactionCode(1L);
        transaction.setCashValue(100L);
        transaction.setTransactionType(TransactionType.CASH_IN);
        Response<Transaction> response = transactionService.createTransaction(transaction);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания транзакции из-за нулевой суммы транзакции")
    void shouldNotCreateTransactionByZeroCash(){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        Person person = new Person();
        person.setId(1L);
        person.setBankAccount(bankAccount);
        Transaction transaction = new Transaction();
        transaction.setBankAccount(person.getBankAccount());
        transaction.setTransactionCode(1L);
        transaction.setCashValue(0L);
        transaction.setTransactionType(TransactionType.CASH_IN);
        Response<Transaction> response = transactionService.createTransaction(transaction);
        Assertions.assertEquals(AppConstants.INCORRECT_SUM, response.getDescription());
    }
}
