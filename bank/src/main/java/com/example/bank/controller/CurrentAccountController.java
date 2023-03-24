package com.example.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.CurrentAccount;
import com.example.bank.entity.Transaction;
import com.example.bank.entity.User;
import com.example.bank.service.CurrentAccountService;
import com.example.bank.service.TransactionService;
import com.example.bank.service.UserService;

@RestController
@RequestMapping("/api/accounts")
public class CurrentAccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private CurrentAccountService currentAccountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{userId}/open")
    public ResponseEntity<CurrentAccount> createCurrentAccount(@PathVariable long userId, @RequestParam(name = "initialCredit", defaultValue = "0") double initialCredit) {
        User user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        CurrentAccount account = currentAccountService.createCurrentAccount(userId, initialCredit);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<Map<String, Object>> getCurrentAccount(@PathVariable long userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        CurrentAccount account = currentAccountService.getCurrentAccount(userId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        List<Transaction> transactions = transactionService.getTransactions(account.getId());
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("account", account);
        result.put("transactions", transactions);
        return ResponseEntity.ok(result);
    }
}
