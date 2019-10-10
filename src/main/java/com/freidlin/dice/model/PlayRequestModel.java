package com.freidlin.dice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PlayRequestModel
{
  private float _wager;
  private float _winChance;

  @Min(3)
  private byte _direction;

  public PlayRequestModel(float wager, float winChance, byte direction)
  {
    _wager = wager;
    _winChance = winChance;
    _direction = direction;
  }

  public float getWager()
  {
    return _wager;
  }

  public float getWinChance()
  {
    return _winChance;
  }

  public byte getDirection()
  {
    return _direction;
  }
}
