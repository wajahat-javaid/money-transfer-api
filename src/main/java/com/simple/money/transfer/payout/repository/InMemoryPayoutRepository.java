package com.simple.money.transfer.payout.repository;

import java.util.Collection;
import java.util.Optional;

import com.simple.money.transfer.payout.model.Payout;

public class InMemoryPayoutRepository implements PayoutRepository 
{

	private static volatile InMemoryPayoutRepository repository = null;
	
	public static InMemoryPayoutRepository getInstance() 
	{
		if( repository == null)
		{
			synchronized (InMemoryPayoutRepository.class) 
			{
				if(repository == null)
				{
					repository = new InMemoryPayoutRepository( );
				}
			}
		}
		return repository;
	}
	
	@Override
	public Collection<Payout> getAllPayouts() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Payout> getPayoutById(Long id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Payout> getPayoutByBankAccount(Long accountId) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Payout> cancelPayOut(Payout cancelPayout) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Payout> createPayout(Payout payout) 
	{
		// TODO Auto-generated method stub
		return null;
	}


  
}
