package com.freidlin.dice.model;

public class PlayerModel
{
  private static PlayerModel _instance = null;
  private static long _balance = 0;

  private PlayerModel()
  {
  }

  public static PlayerModel getInstance()
  {
    if (_instance == null)
    {
      _instance = new PlayerModel();
    }
    return _instance;
  }

  public long getBalance()
  {
    return _balance;
  }

  public void deductWager(long wager)
  {
    _balance -= wager;
  }

  public void applyPayout(long payout)
  {
    _balance += payout;
  }
}
