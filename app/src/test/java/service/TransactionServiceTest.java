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
    void shouldNotGetTransactionByTransactionCode(){
        Long transactionCode = 1L;
        Mockito.when(transactionRepositoryImpl.isPresentTransactionByTransactionCode(transactionCode)).thenReturn(false);
        Response<Long> response = transactionService.getTransactionByTransactionCode(transactionCode);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    void shouldGetTransactionByTransactionCode(){
        Long transactionCode = 1L;
        Mockito.when(transactionRepositoryImpl.isPresentTransactionByTransactionCode(transactionCode)).thenReturn(true);
        Response<Long> response = transactionService.getTransactionByTransactionCode(transactionCode);
        Assertions.assertEquals(AppConstants.TRASACTION_CODE_ALREADY_EXISTS, response.getDescription());
    }

    @Test
    void shouldNotGetTransactionByNullTransactionCode(){
        Long transactionCode = null;
        Response<Long> response = transactionService.getTransactionByTransactionCode(transactionCode);
        Assertions.assertEquals(AppConstants.MISSING_TRANSACTION_CODE, response.getDescription());
    }

    @Test
    void shouldNotGetTransactionsByBankAccountIdByNull(){
        Long id = null;
        Response<List<Transaction>> response = transactionService.getTransactionsByBankAccountId(id);
        Assertions.assertEquals(AppConstants.MISSING_BANK_ACCOUNT_ID, response.getDescription());
    }

    @Test
    void shouldGetTransactionsByBankAccountId(){
        Long id = 1L;
        Mockito.when(transactionRepositoryImpl.getTransactionsByBankAccountId(id)).thenReturn(new ArrayList<>());
        Response<List<Transaction>> response = transactionService.getTransactionsByBankAccountId(id);
        Assertions.assertEquals(AppConstants.SUCCESS, response.getDescription());
    }

    @Test
    void shouldNotCreateTransaction(){
        Response<Transaction> response = transactionService.createTransaction(
                null, null, null, null);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }

    @Test
    void shouldNotCreateTransactionByPersonId(){
        Person person = new Person(null, "1", "1", "1", new BankAccount(), new ArrayList<>());
        Response<Transaction> response = transactionService.createTransaction(
                person, 100L, 11L, TransactionType.CASH_IN);
        Assertions.assertEquals(AppConstants.MISSING_FIELDS, response.getDescription());
    }
}
