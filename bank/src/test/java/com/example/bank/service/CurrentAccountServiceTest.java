package com.example.bank.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bank.entity.CurrentAccount;
import com.example.bank.entity.Transaction;
import com.example.bank.repository.CurrentAccountRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class CurrentAccountServiceTest {

    @Mock
    private CurrentAccountRepository currentAccountRepository;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private CurrentAccountService currentAccountService;

    @Test
    void createCurrentAccount_withInitialCredit() {
        CurrentAccount account = new CurrentAccount();
        account.setUserId(1L);
        account.setBalance(100.0);
        Mockito.when(currentAccountRepository.save(Mockito.any(CurrentAccount.class))).thenReturn(account);
        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getId());
        transaction.setAmount(account.getBalance());
        Mockito.when(transactionService.createTransaction(Mockito.anyLong(),Mockito.anyDouble())).thenReturn(transaction);

        CurrentAccount result = currentAccountService.createCurrentAccount(1L, 100.0);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(100.0, result.getBalance(), 0.0);
    }

    @Test
    void getCurrentAccount_withoutInitialCredit() {
        CurrentAccount account = new CurrentAccount();
        account.setUserId(1L);
        account.setBalance(0.0);
        Mockito.when(currentAccountRepository.save(Mockito.any(CurrentAccount.class))).thenReturn(account);

        CurrentAccount result = currentAccountService.createCurrentAccount(1L, 0.0);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(0.0, result.getBalance(), 0.0);

        Mockito.verify(currentAccountRepository).save(Mockito.any(CurrentAccount.class));
        Mockito.verifyNoInteractions(transactionService);
    }

    @Test
    void getCurrentAccount_existingAccount() {
        CurrentAccount account = new CurrentAccount();
        account.setUserId(1L);
        account.setBalance(100.0);
        Mockito.when(currentAccountRepository.findByUserId(1L)).thenReturn(Optional.of(account));

        CurrentAccount result = currentAccountService.getCurrentAccount(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(100.0, result.getBalance(), 0.0);
    }

    @Test
    void getCurrentAccount_nonExistingAccount() {
        Mockito.when(currentAccountRepository.findByUserId(1L)).thenReturn(Optional.empty());

        CurrentAccount result = currentAccountService.getCurrentAccount(1L);

        assertNull(result);
    }

}
