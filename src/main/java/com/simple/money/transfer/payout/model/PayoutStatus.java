package com.simple.money.transfer.payout.model;


public enum PayoutStatus {
    PROCESSING(1), 
    FAILED(2), 
    SUCCESSFUL(3), 
    CANCELLED(4);

    private int id;

    PayoutStatus(int id) 
    {
        this.id = id;
    }

    public static PayoutStatus valueOf(int id) 
    {
        for(PayoutStatus e : values()) 
        {
            if(e.id == id) return e;
        }

        return null;
    }

    public int getId() 
    {
        return id;
    }
}
