package com.example.bank.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bank.entity.User;
import com.example.bank.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @ExtendWith(MockitoExtension.class)

        @Mock
        private UserRepository userRepository;

        @InjectMocks
        private UserService userService;

        @Test
        void getUser_existingUser() {
            User user = new User();
            user.setId(1L);
            user.setFirstName("Rachel");
            user.setLastName("Mary");
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

            User result = userService.getUser(1L);

            assertNotNull(result);
            assertEquals("Rachel", result.getFirstName());
            assertEquals("Mary", result.getLastName());
        }

        @Test
        void getUser_nonExistingUser() {
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

            User result = userService.getUser(1L);

            assertNull(result);
        }
    }
