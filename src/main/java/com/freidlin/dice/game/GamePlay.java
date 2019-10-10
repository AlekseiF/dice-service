package com.freidlin.dice.game;

import com.freidlin.dice.exceptions.InsufficientFundsException;
import com.freidlin.dice.model.PlayRequestModel;
import com.freidlin.dice.model.PlayerModel;

import java.util.Random;

public class GamePlay
{
  // Super hack
  private PlayerModel _player = PlayerModel.getInstance();

  public GameResult play(PlayRequestModel playRequestModel)
  {
    if (_player.getBalance() < playRequestModel.getWager())
    {
      throw new InsufficientFundsException();
    }

    _player.deductWager(playRequestModel.getWager());

    long payout = 0;
    long result = new Random().nextLong();

    if (result < playRequestModel.getWinChance())
    {
      payout = playRequestModel.getWager() * playRequestModel.getWinChance();
      _player.applyPayout(payout);
    }

    return new GameResult(payout, _player.getBalance(), (byte)1);
  }
}
