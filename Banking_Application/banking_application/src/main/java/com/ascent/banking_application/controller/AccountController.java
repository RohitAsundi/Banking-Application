package com.ascent.banking_application.controller;


import com.ascent.banking_application.entity.Account;
import com.ascent.banking_application.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account){
        Account createAccount = accountService.createAccount(account);
        return null;
    }

}
