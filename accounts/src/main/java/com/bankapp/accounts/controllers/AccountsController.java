package com.bankapp.accounts.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
    @GetMapping("sayHello")
    public String sayHello() {
        return "Hellod from Accounts Service!";
    }
}
