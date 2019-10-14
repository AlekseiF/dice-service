package com.freidlin.dice.controllers;

import com.freidlin.dice.game.GamePlay;
import com.freidlin.dice.game.GameResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;

@RestController
@Validated
public class PlayController
{
  private static final Logger logger = LoggerFactory.getLogger(PlayController.class);

  GamePlay _gamePlay = new GamePlay();

  @RequestMapping("/play")
  public ResponseEntity<GameResult> play(@RequestParam
                         @Min(value = 1, message = "Wager can not be less, than 1")
                         @Max(value = 10000, message = "Wager can not be more, than 10000") int wager,
                                         @RequestParam
                         @Min(value = 2, message = "Win chance can not be less, than 2")
                         @Max(value = 98, message = "Win chance can not be more, than 98") int winChance,
                                         @RequestParam(value = "rollUnder") boolean rollUnder)
  {
    logger.info("{} - player wagered: {}, chosen 'winChance': {}%, with 'rollUnder' set to: {}.", Calendar.getInstance().getTime(), wager, winChance, rollUnder);

    return new ResponseEntity<GameResult>(_gamePlay.play(wager, winChance, rollUnder), HttpStatus.OK) ;
  }
}
