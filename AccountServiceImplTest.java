package com.ascent.banking_application.serviceTest;

import com.ascent.banking_application.entity.Account;
import com.ascent.banking_application.repository.AccountRepo;
import com.ascent.banking_application.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceImplTest {

    @Mock
    AccountRepo accountRepo;
    AccountServiceImpl accountService;

    @BeforeEach
    public  void setUp(){
        accountRepo = Mockito.mock(AccountRepo.class);
        accountService = new AccountServiceImpl(accountRepo);
    }

    @Test
    public void testCreateAccount(){
        Account account = new Account(123456789L,"Rohit",10000.0);

        when(accountRepo.save(account)).thenReturn(account);

        Account savedAccount = accountService.createAccount(account);

        assertNotNull(savedAccount);
        assertEquals(account.getAccountNumber(), savedAccount.getAccountNumber());
    }

    @Test
    public void testGetAccountDetailsByAccountNumber(){
        Long accountNumber = 123456L;

        Account account = new Account(123456L, "Rohit", 10000.0);

        when(accountRepo.findById(accountNumber)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountDetailsByAccountNumber(accountNumber);

        assertNotNull(foundAccount);
        assertEquals(account.getAccountNumber(), foundAccount.getAccountNumber());
    }

    @Test
    public void testGetALlAccountDetails(){
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(123456L, "Rohit", 11000.0));
        accounts.add(new Account(654321L, "Rohan", 12000.1));

        when(accountRepo.findAll()).thenReturn(accounts);

        List<Account> allAccounts = accountService.getALlAccountDetails();

        assertEquals(accounts.size(), allAccounts.size());
        assertNotNull(allAccounts);
    }

    @Test
    public void testDepositAmount(){
        Long accountNumber = 123456L;
        Double initialBalance = 10000.0;
        Double depositAmount = 500.0;

        Account account = new Account(accountNumber, "Rohit", initialBalance);

        when(accountRepo.findById(accountNumber)).thenReturn(Optional.of(account));
        when(accountRepo.save(account)).thenReturn(account);

        Account depositedAmount = accountService.depositAmount(accountNumber, depositAmount);
        assertEquals(initialBalance + depositAmount, depositedAmount.getAccountBalance());
        assertNotNull(depositedAmount);
    }

    @Test
    public void testWithdrawAmount() {

        Long accountNumber = 123456L;
        Double initialBalance = 1000.0;
        Double withdrawalAmount = 200.0;

        Account account = new Account(accountNumber, initialBalance);

        when(accountRepo.findById(accountNumber)).thenReturn(Optional.of(account));

        Account withdrawnAccount = accountService.withdrawAmount(accountNumber, withdrawalAmount);

        assertNotNull(withdrawnAccount);
        assertEquals(accountNumber, withdrawnAccount.getAccountNumber());
        assertEquals(initialBalance - withdrawalAmount, withdrawnAccount.getAccountBalance());

        verify(accountRepo, atLeastOnce()).findById(accountNumber);
        verify(accountRepo, atLeastOnce()).save(account);
    }

    @Test
    public void testCloseAccount(){
        Long accountNumber = 123456L;

        Account account = new Account(accountNumber,"Rohit", 1000.0);
        when(accountRepo.findById(accountNumber)).thenReturn(Optional.of(account));

        accountService.closeAccount(accountNumber);

        verify(accountRepo).findById(accountNumber);
        verify(accountRepo).deleteById(accountNumber);
    }
}
