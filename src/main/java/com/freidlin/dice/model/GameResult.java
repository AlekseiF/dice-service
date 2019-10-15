package com.freidlin.dice.model;

public class GameResult
{
  private final double _payout;
  private final double _currentBalance;
  private final double _multiplier;
  private final boolean _rollUnder;

  public GameResult(double payout, double newBalance, double multiplier, boolean rollUnder)
  {
    _payout = payout;
    _currentBalance = newBalance;
    _multiplier = multiplier;
    _rollUnder = rollUnder;
  }

  public double getPayout()
  {
    return _payout;
  }

  public double getCurrentBalance()
  {
    return _currentBalance;
  }

  public double getMultiplier()
  {
    return _multiplier;
  }

  public boolean getRollUnder()
  {
    return _rollUnder;
  }
}
