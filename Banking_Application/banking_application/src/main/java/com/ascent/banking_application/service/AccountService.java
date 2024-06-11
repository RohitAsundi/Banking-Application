package com.ascent.banking_application.service;

import com.ascent.banking_application.entity.Account;

import java.util.List;

public interface AccountService {

    public Account createAccount(Account account);

    public Account getAccountDetailsByAccountNumber(Long accountNumber);

    public List<Account>getALlAccountDetails();

    public Account depositAmount(Long accountNumber, Double amount);

    public void closeAccount(Long accountNumber);
}
