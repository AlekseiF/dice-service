package com.freidlin.dice.model;

import java.math.BigDecimal;

public class PlayerModel
{
  private static PlayerModel _instance = null;
  private static BigDecimal _balance = new BigDecimal(10000);

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

  public double getBalance()
  {
    return _balance.doubleValue();
  }

  public void deductWager(int wager)
  {
    _balance = _balance.subtract(new BigDecimal(wager));
  }

  public void applyPayout(BigDecimal payout)
  {
    _balance = _balance.add(payout);
  }
}
