package com.simple.money.transfer.account.repository;

import java.util.Collection;
import java.util.Optional;

import com.simple.money.transfer.account.model.Account;

public interface AccountRepository {
  
	Optional<Account> getAccountById(Long id);

	Collection<Account> getAllAccounts();

    Account createAccount(Account account);

    void deleteAccountById(Long id);

	void UpdateBalance(Account account);

}
