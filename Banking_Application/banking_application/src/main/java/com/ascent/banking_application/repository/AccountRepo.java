package com.ascent.banking_application.repository;

import com.ascent.banking_application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Long > {
}
