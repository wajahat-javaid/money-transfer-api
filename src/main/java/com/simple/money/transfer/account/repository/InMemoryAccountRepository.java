package com.simple.money.transfer.account.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.money.transfer.account.model.Account;
import com.simple.money.transfer.common.Currency;
import com.simple.money.transfer.common.datasource.H2DataSource;
import com.simple.money.transfer.common.exception.AccountCreationException;
import com.simple.money.transfer.common.exception.AccountNotExistsException;

public class InMemoryAccountRepository implements AccountRepository 
{
	private static final Logger log = LoggerFactory.getLogger(InMemoryAccountRepository.class);

	private static final String ACCOUNT_TABLE = "account";

	private static final String QUERY_GET_ACCOUNTS = String.format( "SELECT * FROM %S", ACCOUNT_TABLE);
	private static final String QUERY_GET_ACCOUNTS_BY_ID = QUERY_GET_ACCOUNTS + " WHERE id = ?";
	private static final String QUERY_CREATE_ACCOUNT = "INSERT INTO account (title, balance, blocked_amount, currency_id) VALUES (?, ?, ?, ?)";
	private static final String QUERY_DELETE_ACCOUNT = "DELETE FROM account WHERE id  = ?";
	private static final String QUERY_UPDATE_ACCOUNT_BALANCE = "Update account SET balance = ? , blocked_amount = ? WHERE id = ?";


	@Override
	public Optional<Account> getAccountById(Long id) {
		ResultSet rs = null;
		Account acc = null;
		try ( Connection conn = H2DataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_GET_ACCOUNTS_BY_ID) )
		{
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				acc = toAccount(rs);
				log.debug("Retrieve Account By Id: " + acc);
			}
			return Optional.ofNullable( acc );
		} 
		catch (SQLException e) 
		{
			throw new AccountNotExistsException( id, e) ;
		}
		finally
		{
			H2DataSource.quietlyClose(rs);
		}
	}

	@Override
	public Collection<Account> getAllAccounts() {
		Collection<Account> accounts = new ArrayList<>();
		
		ResultSet rs = null;
		Account acc = null;
		try ( Connection conn = H2DataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_GET_ACCOUNTS) )
		{
			rs = stmt.executeQuery();
			while (rs.next()) {
				acc = toAccount(rs);
				accounts.add(acc);
				log.debug("Retrieved Account: " + acc);
			}
			return accounts;
		} 
		catch (SQLException e) 
		{
			log.error( "Exception While Retrieving All Accounts", e );
		}
		finally
		{
			H2DataSource.quietlyClose(rs);
		}
		return accounts;
	}

	@Override
	public Account createAccount(Account account) {
		int rs;
		try ( Connection conn = H2DataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS) )
		{
			stmt.setString(1, account.getTitle());
			stmt.setBigDecimal(2, account.getBalance() );
			stmt.setBigDecimal(3, account.getBlockedAmount());
			stmt.setInt(4, account.getCurrency().getId() );
			
			rs = stmt.executeUpdate();
			if (rs == 0) {
				log.debug("Could not Create an Account for: " + account);
				throw new AccountCreationException( account.getTitle() );
			}
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if( generatedKeys.next() )
			{
				account.setId( generatedKeys.getLong( 1 ) );
				conn.commit();
				return account;
			}
			else 
			{
				throw new AccountCreationException( account.getTitle() );
			}
			
		} 
		catch (SQLException e) 
		{
			throw new AccountCreationException( account.getTitle(),e );
		}
		
	}

	@Override
	public void UpdateBalance(Account account) {
		int rs;
		try ( Connection conn = H2DataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement( QUERY_UPDATE_ACCOUNT_BALANCE ) )
		{
			stmt.setBigDecimal(1, account.getBalance() );
			stmt.setBigDecimal(2, account.getBlockedAmount() );
			stmt.setLong(3, account.getId() );
			
			rs = stmt.executeUpdate();
			if (rs == 0) {
				log.debug("Could not Update Account: " + account );
				throw new AccountNotExistsException( account.getId() );
			}
			conn.commit();
		} 
		catch (SQLException e) 
		{
			throw new AccountNotExistsException( account.getId(),e );
		}

	}

	@Override
	public void deleteAccountById(Long id) {
		int rs;
		try ( Connection conn = H2DataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement( QUERY_DELETE_ACCOUNT ) )
		{
			stmt.setLong(1, id );
			rs = stmt.executeUpdate();
			if (rs == 0) {
				log.debug("Could not Delete Account for id: " + id);
				throw new AccountNotExistsException( id );
			}
			conn.commit();
		} 
		catch (SQLException e) 
		{
			throw new AccountNotExistsException( id,e );
		}

	}

	private Account toAccount(ResultSet rs) throws SQLException 
	{
		Account account = new Account();
		account.setBalance(rs.getBigDecimal("balance"));
		account.setBlockedAmount(rs.getBigDecimal("blocked_amount"));
		account.setCurrency( Currency.valueOf( rs.getInt("currency_id") ) );
		account.setTitle(rs.getString("title"));
		account.setId(rs.getLong("id"));
		return account;

	}

}
