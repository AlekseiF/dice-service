package com.freidlin.dice.controllers;

import com.freidlin.dice.game.GamePlay;
import com.freidlin.dice.game.GameResult;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class PlayControllerTest
{
  @Autowired
  private PlayController _playController;

  @Test
  void testCorrectPlayRequest()
  {
    int wager = 2;
    int winChance = 4;
    boolean rollUnder = true;
    ResponseEntity<GameResult> result = _playController.play(wager, winChance, rollUnder);

    assertThat(result.getStatusCode(), is(HttpStatus.OK));
  }

  /*@Test
  void testIncorretWagerPlayRequest()
  {
    _playController._gamePlay = new GamePlay();
    int wager = 0;
    int winChance = 4;
    boolean rollUnder = true;
    ResponseEntity<GameResult> result = _playController.play(wager, winChance, rollUnder);

    assertThat(result.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
  }*/

  /*@Test
  void testIncorretWinChancePlayRequest()
  {
    _playController._gamePlay = new GamePlay();
    int wager = 2;
    int winChance = 99;
    boolean rollUnder = true;
    ResponseEntity<GameResult> result = _playController.play(wager, winChance, rollUnder);

    assertThat(result.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
  }*/
}
