package java.service;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.impl.BankAccountRepositoryImpl;
import com.furiosaming.walletService.service.constants.AppConstants;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.BankAccountService;
import com.furiosaming.walletService.service.service.TransactionService;
import com.furiosaming.walletService.service.service.impl.BankAccountServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankAccountServiceTest  {
    BankAccountService bankAccountService;
    @Mock
    TransactionService transactionService;
    @Mock
    BankAccountRepositoryImpl bankAccountRepositoryImpl;

    public BankAccountServiceTest(){
        MockitoAnnotations.openMocks(this);
        this.bankAccountService = new BankAccountServiceImpl(bankAccountRepositoryImpl, transactionService);
    }

    @Test
    @DisplayName("Тест на успешный поиск счета по id владельца счета")
    void shouldGetBankAccountByPersonId(){
        Long id = 1L;
        Mockito.when(bankAccountRepositoryImpl.getBankAccountByPersonId(id)).thenReturn(new BankAccount());
        Response<BankAccount> response = bankAccountService.getBankAccountByPersonId(id);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска счета из-за отсутствия id владельца счета")
    void shouldNotGetBankAccountByPersonIdByNullId(){
        Long id = null;
        Response<BankAccount> response = bankAccountService.getBankAccountByPersonId(id);
        Assertions.assertEquals(AppConstants.MISSING_PERSON_ID, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска счета из-за ввода несуществующего id владельца счета")
    void shouldNotGetBankAccountByPersonId(){
        Long id = 1L;
        Response<BankAccount> response = bankAccountService.getBankAccountByPersonId(id);
        Mockito.when(bankAccountRepositoryImpl.getBankAccountByPersonId(id)).thenReturn(null);
        Assertions.assertEquals(AppConstants.BANK_ACCOUNT_NOT_FOUND, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку поиска счета из-за отсутствия владельца счета")
    void shouldNotCreateBankAccountByPersonNull(){
        Response<BankAccount> response = bankAccountService.createBankAccount(null);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку создания счета")
    void shouldNotCreateBankAccount(){
        Mockito.when(bankAccountRepositoryImpl.createBankAccount(new Person())).thenReturn(null);
        Person person = new Person();
        person.setId(1L);
        Response<BankAccount> response = bankAccountService.createBankAccount(person);
        Assertions.assertEquals(AppConstants.FAILED_TO_CREATE, response.getDescription());
    }

    @Test
    @DisplayName("Тест на успешное создание счета")
    void shouldCreateBankAccount(){
        Person person = new Person();
        person.setId(1L);
        Mockito.when(bankAccountRepositoryImpl.createBankAccount(person)).thenReturn(new BankAccount());
        Response<BankAccount> response = bankAccountService.createBankAccount(person);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на неудачную попытку операции по счету из-за ввода уже существующего" +
            "кода транзакции")
    void shouldNotCashInOutByTransactionCode(){
        Person person = new Person();
        person.setId(1L);
        Transaction transaction = new Transaction();
        transaction.setBankAccount(person.getBankAccount());
        transaction.setTransactionCode(1L);
        transaction.setCashValue(100L);
        transaction.setTransactionType(TransactionType.CASH_IN);
        Mockito.when(transactionService.getTransactionByTransactionCode(transaction.getTransactionCode())).thenReturn(
                new Response.Builder<Long>().alreadyExist(AppConstants.TRASACTION_CODE_ALREADY_EXISTS).build());
        Response<Long> response = bankAccountService.cashInOut(
                transaction);
        Assertions.assertEquals(AppConstants.TRASACTION_CODE_ALREADY_EXISTS, response.getDescription());
    }

    @Test
    @DisplayName("Тест на успешное проведение операции по счету")
    void shouldCashInOut(){
        Person person = new Person();
        person.setId(1L);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCashValue(0L);
        person.setBankAccount(bankAccount);
        Transaction transaction = new Transaction();
        transaction.setBankAccount(person.getBankAccount());
        transaction.setTransactionCode(1L);
        transaction.setCashValue(100L);
        transaction.setTransactionType(TransactionType.CASH_IN);
        Mockito.when(transactionService.getTransactionByTransactionCode(transaction.getTransactionCode())).thenReturn(
                new Response.Builder<Long>().success(1L).build());
        Mockito.when(transactionService.createTransaction(transaction))
                .thenReturn(new Response.Builder<Transaction>().success(new Transaction()).build());
        Response<Long> response = bankAccountService.cashInOut(transaction);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }
}