package com.freidlin.dice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PlayRequestModel
{
  private long _wager;
  private long _winChance;
  private byte _direction;

  public PlayRequestModel(long wager, long winChance, byte direction)
  {
    _wager = wager;
    _winChance = winChance;
    _direction = direction;
  }

  public long getWager()
  {
    return _wager;
  }

  public long getWinChance()
  {
    return _winChance;
  }

  public byte getDirection()
  {
    return _direction;
  }
}
