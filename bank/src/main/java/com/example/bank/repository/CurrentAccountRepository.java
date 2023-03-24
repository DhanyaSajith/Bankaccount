package com.example.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.entity.CurrentAccount;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount,Long> {
     Optional<CurrentAccount> findByUserId(long userId);
}
