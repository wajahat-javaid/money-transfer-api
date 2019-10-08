package com.simple.money.transfer.common;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.money.transfer.common.exception.AccountCreationException;
import com.simple.money.transfer.common.exception.AccountNotExistsException;

/**
 * @author WJAVAID
 *
 */
@Provider
public class ExceptionResponseMapper implements ExceptionMapper<Throwable> 
{
    private static final Logger log = LoggerFactory.getLogger(ExceptionResponseMapper.class);

    @Override
    public Response toResponse(Throwable exception) 
    {
        ExceptionResponse exceptionResponse = null;
        Response.ResponseBuilder serverError = Response.serverError().type(MediaType.APPLICATION_JSON_TYPE);

        if (exception instanceof WebApplicationException)
        {
        	exceptionResponse = new ExceptionResponse( "Unexpected", "WebApplicationException", exception.getMessage());
            serverError = serverError.status(((WebApplicationException) exception).getResponse().getStatus());
        } 
        else if (exception instanceof AccountCreationException) 
        {
        	exceptionResponse  = new ExceptionResponse( "Resource Creation", "AccountCreationException", exception.getMessage() );
            serverError = serverError.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        else if ( exception instanceof AccountNotExistsException )
        {
        	exceptionResponse  = new ExceptionResponse( "Resource Availability", "AccountNotExistsException", exception.getMessage() );
            serverError = serverError.status(Response.Status.INTERNAL_SERVER_ERROR);
        } 
        else 
        {
        	exceptionResponse  = new ExceptionResponse( "Unexpected", "InternalServerError", exception.getMessage() );
            serverError = serverError.status(Response.Status.INTERNAL_SERVER_ERROR);
        }

        log.error("Uncaught exception", exception);
        return serverError.entity(exceptionResponse).build();
    }

}