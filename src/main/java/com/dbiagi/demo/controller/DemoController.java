package com.dbiagi.demo.controller;

import com.dbiagi.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final UserRepository userRepository;

    DemoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/count")
    public Response countUsers() {
        long count = userRepository.count();
        return new Response("Total users: " + count);
    }

    @GetMapping("/hello")
    public Response hello() {
        return new Response("Hello, World!");
    }

    public static class Response {
        public String data;
        Response(String data) {
            this.data = data;
        }
    }
}


