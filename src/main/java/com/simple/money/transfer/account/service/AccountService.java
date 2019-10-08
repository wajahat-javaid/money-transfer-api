package com.simple.money.transfer.account.service;

import java.util.Collection;

import com.simple.money.transfer.account.model.Account;

/**
 */
public interface AccountService {
	Account getAccountById(Long id);

	Collection<Account> getAllAccounts();

	Account createAccount(Account account);

    void deleteAccountById(Long id);

	void UpdateBalance(Account account);
}
