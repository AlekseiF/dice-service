package com.freidlin.dice.controllers;

import com.freidlin.dice.services.GamePlayService;
import com.freidlin.dice.model.GameResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;

@RestController
@Validated
public class PlayController
{
  private static final Logger logger = LoggerFactory.getLogger(PlayController.class);

  @Autowired
  GamePlayService _gamePlayService;

  @GetMapping("/play")
  public GameResult play(@RequestParam(name = "wager")
                           @Min(1) @Max(1000) int wager,
                         @RequestParam(name="winChance")
                           @Min(2) @Max(98) int winChance,
                         @RequestParam(name="rollUnder") boolean rollUnder)
  {
    logger.info("{} - player wagered: {}, chosen 'winChance': {}%, with 'rollUnder' set to: {}.",
      Calendar.getInstance().getTime(), wager, winChance, rollUnder);

    return _gamePlayService.play(wager, winChance, rollUnder);
  }
}
