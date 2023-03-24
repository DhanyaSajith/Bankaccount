package com.example.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.entity.Transaction;
import com.example.bank.repository.TransactionRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testCreateTransaction() {
        Transaction mockTransaction = mock(Transaction.class);
        when(transactionRepository.save(mockTransaction)).thenReturn(mockTransaction);
        Transaction actualTransaction = transactionService.createTransaction(1L, 100.0);
        assertEquals(mockTransaction, actualTransaction);
    }
    @Test
    public void testGetTransactions() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(mock(Transaction.class));
        mockTransactions.add(mock(Transaction.class));
        when(transactionRepository.findByAccountId(anyLong())).thenReturn(mockTransactions);
        List<Transaction> actualTransactions = transactionService.getTransactions(1L);
        assertEquals(mockTransactions.size(), actualTransactions.size());
    }

}