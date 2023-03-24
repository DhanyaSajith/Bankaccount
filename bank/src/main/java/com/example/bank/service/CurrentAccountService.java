package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.CurrentAccount;
import com.example.bank.repository.CurrentAccountRepository;

@Service
public class CurrentAccountService {

    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    private TransactionService transactionService;

    //Create a new Account object.
    public CurrentAccount createCurrentAccount(long userId, double initialCredit) {
        CurrentAccount account = new CurrentAccount();
        account.setUserId(userId);
        account.setBalance(initialCredit);
        account = currentAccountRepository.save(account);

    //If initial credit is greater than 0,add a transaction to the new account created.
        if (initialCredit > 0) {
            transactionService.createTransaction(account.getId(), initialCredit);
        }

        return account;
    }

    public CurrentAccount getCurrentAccount(long userId) {
        return currentAccountRepository.findByUserId(userId).orElse(null);
    }
}
