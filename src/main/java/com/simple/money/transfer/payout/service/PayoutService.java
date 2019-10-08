package com.simple.money.transfer.payout.service;

import java.util.Collection;

import com.simple.money.transfer.payout.model.Payout;

public interface PayoutService {

	Collection<Payout> getAllPayouts();
	Payout getPayoutById(Long id);
	Payout getPayoutByBankAccount(Long id);
	Payout cancelPayout( Payout payout );
	Payout createPayout( Payout payout );
}
