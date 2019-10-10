package com.freidlin.dice.exceptions;

public class InsufficientFundsException extends RuntimeException
{
    public InsufficientFundsException()
    {
        super("Insufficient funds. Balance can not be lower, than wager.");
    }
}
