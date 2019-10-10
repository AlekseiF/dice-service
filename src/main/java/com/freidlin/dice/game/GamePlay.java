package com.freidlin.dice.game;

import com.freidlin.dice.exceptions.InvalidBetException;
import com.freidlin.dice.model.PlayRequestModel;
import com.freidlin.dice.model.PlayerModel;

import java.util.Random;

public class GamePlay
{
  // Super hack
  private PlayerModel _player = PlayerModel.getInstance();

  public GameResult play(PlayRequestModel playRequestModel)
  {
    /*if (_player.getBalance() < playRequestModel.getWager())
    {
      throw new InvalidBetException();
    }*/

    Random rng = new Random();
    float result = rng.nextFloat();

    _player.setBalance(2);
    return new GameResult(0, _player.getBalance(), (byte)1, true);
  }
}
