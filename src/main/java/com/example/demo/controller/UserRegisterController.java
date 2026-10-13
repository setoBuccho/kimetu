package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegisterController {

    private final UsersService usersService;

    @GetMapping("/user/register")
    public String register() {
        return "diagnosis/user/user-register";
    }

    @PostMapping("/user/register")
    public String register(Users user) {

        usersService.saveUser(user);

        return "redirect:/users";
    }
}