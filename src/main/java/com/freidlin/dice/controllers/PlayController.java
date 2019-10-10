package com.freidlin.dice.controllers;

import com.freidlin.dice.game.GamePlay;
import com.freidlin.dice.game.GameResult;
import com.freidlin.dice.model.PlayRequestModel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayController
{
  @RequestMapping("/play")
  public GameResult play(@RequestParam(value = "wager") float wager,
                         @RequestParam(value = "winChance") float winChance,
                         @RequestParam(value = "direction") byte direction)
  {
    return new GamePlay().play(new PlayRequestModel(wager, winChance, direction));
  }
}
