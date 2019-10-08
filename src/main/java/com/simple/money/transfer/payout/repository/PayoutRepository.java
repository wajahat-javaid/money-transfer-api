package com.simple.money.transfer.payout.repository;

import java.util.Collection;
import java.util.Optional;

import com.simple.money.transfer.payout.model.Payout;

public interface PayoutRepository {
  
	Collection<Payout> getAllPayouts();
	Optional<Payout> getPayoutById(Long id);
	Collection<Payout> getPayoutByBankAccount(Long accountId);
	Optional<Payout> cancelPayOut(Payout cancelPayout);
	Optional<Payout> createPayout(Payout payout);
}
