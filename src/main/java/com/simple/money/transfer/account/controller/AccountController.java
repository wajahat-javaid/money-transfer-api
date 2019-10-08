package com.simple.money.transfer.account.controller;


import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.simple.money.transfer.account.model.Account;
import com.simple.money.transfer.account.service.AccountService;
import com.simple.money.transfer.account.service.SimpleAccountService;
import com.simple.money.transfer.common.exception.AccountCreationException;
import com.simple.money.transfer.common.exception.AccountNotExistsException;

/**
 * @author WJAVAID
 *
 */
@Path(AccountController.BASE_URL)
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {
    
	public static final String BASE_URL = "/account";
    public static final String PATH_PARAM_ID = "id";

    private final AccountService accountService = SimpleAccountService.getInstance();

    /**
     * @return List of all Account objects created in the system.
     *
     */
    @GET
    public Response getAllBankAccounts() 
    {
        Collection<Account> accounts;

        accounts = accountService.getAllAccounts();

        if (accounts.size() < 1) {
            Response.noContent().build();
        }

        return Response.ok(accounts).build();
    }

    /**
     * @param id The ID of Bank Account
     */
    @GET
    @Path("{" + PATH_PARAM_ID + "}")
    public Response getBankAccountById(@PathParam(PATH_PARAM_ID) Long id) 
    {
        Account account;

        account = accountService.getAccountById(id);

        if ( account.getId() == null) {
            throw new WebApplicationException("The Specified Account does not exist", Response.Status.NOT_FOUND);
        }

        return Response.ok(account).build();
    }

    @PUT
    public Response updateAccountBalance(Account account) throws AccountNotExistsException 
    {
        accountService.UpdateBalance(account);

        return Response.ok(account).build();
    }

   
    @POST
    public Response createBankAccount(Account account) throws AccountCreationException 
    {
        Account createdAccount;

        createdAccount = accountService.createAccount(account);

        return Response.ok(createdAccount).build();
    }
    
    @DELETE
    @Path("{" + PATH_PARAM_ID + "}")
    public Response deleteAccount( @PathParam(PATH_PARAM_ID) Long id) throws AccountNotExistsException
    {
    	accountService.deleteAccountById(id);
    	
    	return Response.ok().build();
    }
}
