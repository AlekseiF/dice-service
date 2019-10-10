package com.freidlin.dice.game;

import com.freidlin.dice.model.PlayRequestModel;

import java.util.Random;

public class GameResult
{
  private final long _payout;
  private final long _currentBalance;
  private final byte _direction;
  private final boolean _win;

  public GameResult(long payout, long newBalance, byte direction, boolean win)
  {
    _payout = payout;
    _currentBalance = newBalance;
    _direction = direction;
    _win = win;
  }

  public long getPayout()
  {
    return _payout;
  }

  public long getCurrentBalance()
  {
    return _currentBalance;
  }

  public byte getDirection()
  {
    return _direction;
  }

  public boolean getWin()
  {
    return _win;
  }
}
