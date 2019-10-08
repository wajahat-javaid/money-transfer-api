package com.simple.money.transfer.account.service;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.money.transfer.account.model.Account;
import com.simple.money.transfer.account.repository.AccountRepository;
import com.simple.money.transfer.account.repository.InMemoryAccountRepository;

public class SimpleAccountService implements AccountService
{
	private static final Logger log = LoggerFactory.getLogger( SimpleAccountService.class );
	
	private static volatile SimpleAccountService service;
	
	private AccountRepository accountRepository  = new InMemoryAccountRepository();
	

	public static SimpleAccountService getInstance()
	{
		if( service == null)
		{
			synchronized (SimpleAccountService.class) 
			{
				if( service == null )
					service = new SimpleAccountService();
			}
		}
		return service;
	}
	
	@Override
	public Account getAccountById(Long id) {
		Optional<Account> account = accountRepository.getAccountById(id);
		return account.orElseGet( () ->  new Account());
	}

	@Override
	public Collection<Account> getAllAccounts() {
		return accountRepository.getAllAccounts();
	}

	/*Create, Update, Delete Expect a resource to be in System, in this 
	 case, Exception will be thrown in an Un Expected scenario
	*/
	@Override
	public Account createAccount(Account account) {
		return accountRepository.createAccount(account);
	}

	@Override
	public void deleteAccountById(Long id) {
		accountRepository.deleteAccountById(id);
		
	}

	@Override
	public void UpdateBalance(Account account) {
		accountRepository.UpdateBalance(account);
		
	}
	
	
}
