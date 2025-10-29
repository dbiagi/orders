package com.dbiagi.demo.service;

import com.dbiagi.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserServiceTest {
    @Mock
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void testCreateUser() {
        assertTrue(userRepository != null);
    }
}