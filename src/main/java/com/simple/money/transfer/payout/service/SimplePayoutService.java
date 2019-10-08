package com.simple.money.transfer.payout.service;


import java.math.BigDecimal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.money.transfer.payout.model.Payout;
import com.simple.money.transfer.payout.repository.InMemoryPayoutRepository;
import com.simple.money.transfer.payout.repository.PayoutRepository;



public class SimplePayoutService implements PayoutService 
{
	private static final Logger log = LoggerFactory.getLogger(PayoutService.class);

	private static volatile SimplePayoutService service;
	
	private CurrencyConverterService currencyConverterService;
	PayoutRepository payoutRepository = InMemoryPayoutRepository.getInstance();

	private SimplePayoutService(CurrencyConverterService currencyConverterService) {
		this.currencyConverterService = currencyConverterService;
	}

	public static SimplePayoutService getInstance(CurrencyConverterService currencyConverterService ) 
	{
		if( service == null)
		{
			synchronized (SimplePayoutService.class) 
			{
				if(service == null){
					service = new SimplePayoutService(currencyConverterService );
				}
			}
		}
		return service;
	}

	@Override
	public Collection<Payout> getAllPayouts() {
		return PayoutDto.getAllPayouts();
	}

	@Override
	private Collection<Payout> getPayoutByBankAccount(Long banckAccountNumber) {
		return PayoutDto.getAllPayoutIdsByStatus(banckAccountNumber);
	}

	@Override
	public Payout getPayoutById(Long id) {
		return PayoutDto.getPayoutById(id);
	}

	/**
	 * Make it possible to create money transfer from one account to another.
	 * The result of execution is created Payout with actual status. Usually it is "IN PROGRESS"
	 *
	 * The Payout <code>fromBankAccount</code> and <code>toBankAccount</code> may have not specified any
	 * fields except id
	 *
	 * @return Payout object with the actual ID
	 */
	public Payout createPayout(Payout Payout) throws ObjectModificationException {
		if (Payout.getFromBankAccountId() == null || Payout.getToBankAccountId() == null) {
			throw new ObjectModificationException(ExceptionType.OBJECT_IS_MALFORMED,
					"The Payout has not provided from Bank Account or to Bank Account values");
		}
		if (Payout.getFromBankAccountId().equals(Payout.getToBankAccountId())) {
			throw new ObjectModificationException(ExceptionType.OBJECT_IS_MALFORMED,
					"The sender and recipient should not be same");
		}
		if (Payout.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ObjectModificationException(ExceptionType.OBJECT_IS_MALFORMED,
					"The amount should be more than 0");
		}

		return PayoutDto.createPayout(Payout);
	}

	@Override
	public Payout getPayoutByBankAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payout cancelPayout(Payout payout) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Possibility to add a Payout status of Pending and Execute in batches
	 */
	/*public void executePayouts() 
    {
        log.info("Starting of Payout executor");
        Collection<Long> plannedPayoutIds = getAllPayoutIdsByStatus(PayoutStatus.PLANNED);

        for (Long PayoutId : plannedPayoutIds) {
            try {
                PayoutDto.executePayout(PayoutId);
            } catch (ObjectModificationException e) {
                log.error("Could not execute Payout with id %d", PayoutId, e);
            }
        }
        log.info("Payout executor ended");
    }*/
}
