package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.User;
import com.example.bank.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(long id)
    {
        return userRepository.findById(id).orElse(null);
    }
}
