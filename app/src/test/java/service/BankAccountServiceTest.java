package service;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    void shouldGetBankAccountByPersonId(){
        Long id = 1L;
        Mockito.when(bankAccountRepositoryImpl.getBankAccountByPersonId(id)).thenReturn(new BankAccount());
        Response<BankAccount> response = bankAccountService.getBankAccountByPersonId(id);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    void shouldNotGetBankAccountByPersonIdByNullId(){
        Long id = null;
        Response<BankAccount> response = bankAccountService.getBankAccountByPersonId(id);
        Assertions.assertEquals(AppConstants.MISSING_PERSON_ID, response.getDescription());
    }

    @Test
    void shouldNotGetBankAccountByPersonId(){
        Long id = 1L;
        Response<BankAccount> response = bankAccountService.getBankAccountByPersonId(id);
        Mockito.when(bankAccountRepositoryImpl.getBankAccountByPersonId(id)).thenReturn(null);
        Assertions.assertEquals(AppConstants.BANK_ACCOUNT_NOT_FOUND, response.getDescription());
    }

    @Test
    void shouldNotCreateBankAccountByPersonNull(){
        Response<BankAccount> response = bankAccountService.createBankAccount(null);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    void shouldNotCreateBankAccountByPersonIdNull(){
        Response<BankAccount> response = bankAccountService.createBankAccount(new Person());
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    void shouldNotCreateBankAccount(){
        Mockito.when(bankAccountRepositoryImpl.createBankAccount(new Person())).thenReturn(null);
        Person person = new Person();
        person.setId(1L);
        Response<BankAccount> response = bankAccountService.createBankAccount(person);
        Assertions.assertEquals(AppConstants.FAILED_TO_CREATE, response.getDescription());
    }

    @Test
    void shouldCreateBankAccount(){
        Person person = new Person();
        person.setId(1L);
        Mockito.when(bankAccountRepositoryImpl.createBankAccount(person)).thenReturn(new BankAccount());
        Response<BankAccount> response = bankAccountService.createBankAccount(person);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    void shouldNotCashInOutByZeroCash(){
        Long cash = 0L;
        Person person = new Person();
        person.setId(1L);
        Response<Long> response = bankAccountService.cashInOut(
                person, 111L, cash, TransactionType.CASH_IN);
        Assertions.assertEquals(AppConstants.INCORRECT_SUM, response.getDescription());
    }

    @Test
    void shouldNotCashInOutByCashNull(){
        Long cash = null;
        Person person = new Person();
        person.setId(1L);
        Response<Long> response = bankAccountService.cashInOut(
                person, 111L, cash, TransactionType.CASH_IN);
        Assertions.assertEquals(AppConstants.INCORRECT_SUM, response.getDescription());
    }

    @Test
    void shouldNotCashInOutByTransactionCode(){
        Long cash = 100L;
        Person person = new Person();
        person.setId(1L);
        Long transactionCode = 1L;
        Mockito.when(transactionService.getTransactionByTransactionCode(transactionCode)).thenReturn(
                new Response.Builder<Long>().alreadyExist(AppConstants.TRASACTION_CODE_ALREADY_EXISTS).build());
        Response<Long> response = bankAccountService.cashInOut(
                person, transactionCode, cash, TransactionType.CASH_IN);
        Assertions.assertEquals(AppConstants.TRASACTION_CODE_ALREADY_EXISTS, response.getDescription());
    }

    @Test
    void shouldCashInOut(){
        Long cash = 100L;
        Person person = new Person();
        person.setId(1L);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCashValue(0L);
        person.setBankAccount(bankAccount);
        Long transactionCode = 1L;
        Mockito.when(transactionService.getTransactionByTransactionCode(transactionCode)).thenReturn(
                new Response.Builder<Long>().success(transactionCode).build());
        Mockito.when(transactionService.createTransaction(person, cash, transactionCode, TransactionType.CASH_IN))
                .thenReturn(new Response.Builder<Transaction>().success(new Transaction()).build());
        Response<Long> response = bankAccountService.cashInOut(
                person, transactionCode, cash, TransactionType.CASH_IN);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }
}
