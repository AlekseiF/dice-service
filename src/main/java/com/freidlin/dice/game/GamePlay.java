package com.freidlin.dice.game;

import com.freidlin.dice.exceptions.InsufficientFundsException;
import com.freidlin.dice.model.PlayerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Random;

public class GamePlay
{
  private static final Logger logger = LoggerFactory.getLogger(GamePlay.class);
  // Super hack
  private PlayerModel _player = PlayerModel.getInstance();

  private final int MAX_WIN_CHANCE = 99;
  private final int MIN_WIN_CHANCE = 2;

  public GameResult play(int wager, int winChance, boolean rollUnder)
  {
    validateBalance(wager);

    return executeSingleGame(wager, winChance, rollUnder);
  }

  private void validateBalance(int wager)
  {
    if (_player.getBalance() < wager)
    {
      throw new InsufficientFundsException();
    }
  }

  private GameResult executeSingleGame(int wager, int winChance, boolean rollUnder)
  {
    _player.deductWager(wager);

    BigDecimal payout = new BigDecimal(0);
    BigDecimal multiplier = getMultiplier(winChance);
    int result = generateResult(rollUnder);

    // TODO: consider rollUnder, rollOver logic. Currently rollUnder param does nothing.
    if (result < winChance)
    {
      payout = new BigDecimal(wager).multiply(multiplier);
      _player.applyPayout(payout);
    }

    logger.info("{} - player 'payout': {}, current balance: {}, with calculated 'multiplier': {}.", Calendar.getInstance().getTime(), payout, _player.getBalance(), multiplier);

    return new GameResult(payout.doubleValue(), _player.getBalance(), multiplier.doubleValue(), rollUnder);
  }

  private int generateResult(boolean rollUnder)
  {
    return new Random().nextInt((MAX_WIN_CHANCE - MIN_WIN_CHANCE) + 1) + MIN_WIN_CHANCE;
  }

  private BigDecimal getMultiplier(int winChance)
  {
    return new BigDecimal(Double.valueOf(MAX_WIN_CHANCE) / Double.valueOf(winChance));
  }
}
