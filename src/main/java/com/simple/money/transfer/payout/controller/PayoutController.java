package com.simple.money.transfer.payout.controller;

import com.github.shimopus.revolutmoneyexchange.exceptions.ObjectModificationException;
import com.github.shimopus.revolutmoneyexchange.model.Transaction;
import com.github.shimopus.revolutmoneyexchange.service.ConstantMoneyExchangeService;
import com.github.shimopus.revolutmoneyexchange.service.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(TransactionsController.BASE_URL)
@Produces(MediaType.APPLICATION_JSON)
public class PayoutController {
    private final Logger log = LoggerFactory.getLogger(TransactionsController.class);

    public static final String BASE_URL = "/transactions";
    public static final String GET_TRANSACTION_BY_ID_PATH = "id";

    private TransactionsService transactionsService = TransactionsService.getInstance(new ConstantMoneyExchangeService());

    
    
    /**
     * @param id
     * Get Payout detail by ID
     * @return
     */
    @GET()
    @Path("{" + GET_TRANSACTION_BY_ID_PATH + "}")
    public Response getTransactionById(@PathParam(GET_TRANSACTION_BY_ID_PATH) Long id) {
        return Response.ok().entity(transactionsService.getTransactionById(id)).build();
    }
    
    
    /**
     * Gives all Payouts with detials
     *
     */
    @GET
    public Response getAllTransactions() {
        return Response.ok().entity(transactionsService.getAllTransactions()).build();
    }

    /**
     * Make it possible to create money transfer from one account to another.
     * The result of execution is created transaction with actual status. Usually it is "IN PROGRESS".
     * The transaction execution process is asynchronous and controlled by the system itself
     *
     * @param Performs a payout operation between the bank accounts:
     *                    <code>fromBankAccountId, toBankAccountId, amount, currency</code>. All other parameters
     *                    will be ignored and created by the system
     *
     * @return Payout Object with System generated ID for the Payout
     */
    @POST()
    public Response doPayout(Payout transaction) throws ObjectModificationException {
        transaction = transactionsService.createTransaction(transaction);

        return Response.ok().entity(transaction).build();
    }
}
