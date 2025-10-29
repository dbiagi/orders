package com.dbiagi.demo.service;

import com.dbiagi.demo.entity.User;
import com.dbiagi.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CreateUserService {
    Logger logger = LoggerFactory.getLogger(CreateUserService.class);

    private final UserRepository userRepository;

    CreateUserService(
            UserRepository repository
    ) {
        this.userRepository = repository;
    }

    public void create(String name, String email) {
        var user = new User();
        user.name = name;
        user.email = email;

        try {
            userRepository.save(user);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
    }
}
