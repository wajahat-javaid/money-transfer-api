package com.simple.money.transfer.common;

/**
 * @author WJAVAID
 *
 *Generic Reponse to be returned in case of Exceptions
 */
public class ExceptionResponse 
{
    
	private String type;
    private String name;
    private String message;

    public ExceptionResponse() 
    {
    }

    public ExceptionResponse(String type, String name, String message) 
    {
        this.type = type;
        this.name = name;
        this.message = message;
    }

    public String getType() 
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }
}
