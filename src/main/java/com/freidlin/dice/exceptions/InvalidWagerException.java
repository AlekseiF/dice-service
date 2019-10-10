package com.freidlin.dice.exceptions;

public class InvalidWagerException extends RuntimeException
{
  public InvalidWagerException()
  {
    super("Invalid balance");
  }
}
