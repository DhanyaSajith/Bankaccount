package com.example.bank.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.bank.entity.User;
import com.example.bank.service.CurrentAccountService;
import com.example.bank.service.UserService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private CurrentAccountService currentAccountService;
   @InjectMocks
    private UserController userController;

    @Test
    public void getUser() {
        // Setup
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstName("Rachel");
        user.setLastName("Mary");
        when(userService.getUser(userId)).thenReturn(user);

        // Execution
        ResponseEntity<User> response = userController.getUser(userId);

        // Verification
        verify(userService).getUser(userId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserNotFound() {
        // Setup
        long userId = 1L;
        when(userService.getUser(userId)).thenReturn(null);

        // Execution
        ResponseEntity<User> response = userController.getUser(userId);

        // Verification
        verify(userService).getUser(userId);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}