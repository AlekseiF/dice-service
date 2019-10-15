package com.freidlin.dice.services;

import com.freidlin.dice.exceptions.InsufficientFundsException;
import com.freidlin.dice.model.GameResult;
import com.freidlin.dice.model.PlayerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Random;

@Component
public class GamePlayService
{
  private static final Logger logger = LoggerFactory.getLogger(GamePlayService.class);
  // Super hack
  private PlayerModel _player = PlayerModel.getInstance();

  private final int MAX_WIN_CHANCE = 99;
  private final int MIN_WIN_CHANCE = 2;
  private BigDecimal _payout = BigDecimal.ZERO;
  private BigDecimal _multiplier = BigDecimal.ZERO;

  public GameResult play(int wager, int winChance, boolean rollUnder)
  {
    reset();

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

    _multiplier = getMultiplier(winChance);
    int result = generateResult(rollUnder);

    // TODO: consider rollUnder, rollOver logic. Currently rollUnder param does nothing.
    if (result < winChance)
    {
      _payout = new BigDecimal(wager).multiply(_multiplier);
      _player.applyPayout(_payout);
    }

    logger.info("{} - player 'payout': {}, current balance: {}, with calculated 'multiplier': {}.", Calendar.getInstance().getTime(), _payout, _player.getBalance(), _multiplier);

    return new GameResult(_payout.doubleValue(), _player.getBalance(), _multiplier.doubleValue(), rollUnder);
  }

  private int generateResult(boolean rollUnder)
  {
    return new Random().nextInt((MAX_WIN_CHANCE - MIN_WIN_CHANCE) + 1) + MIN_WIN_CHANCE;
  }

  private BigDecimal getMultiplier(int winChance)
  {
    return new BigDecimal(Double.valueOf(MAX_WIN_CHANCE) / Double.valueOf(winChance));
  }

  void reset()
  {
    _payout = BigDecimal.ZERO;
    _multiplier = BigDecimal.ZERO;
  }
}
