package com.freidlin.dice.exceptions;

public class InvalidBetException extends RuntimeException
{
  public InvalidBetException()
  {
    super("Invalid balance");
  }
}
