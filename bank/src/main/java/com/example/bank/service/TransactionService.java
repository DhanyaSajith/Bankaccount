package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.Transaction;
import com.example.bank.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    public Transaction createTransaction(long accountId, double amount)
    {
        // Create a new transaction object and save the transaction to the database
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        return transaction;
    }
        // Retrieve all transactions for the specified accountID
    public List<Transaction> getTransactions(long accountId)
    {
        return transactionRepository.findByAccountId(accountId);
    }
}
