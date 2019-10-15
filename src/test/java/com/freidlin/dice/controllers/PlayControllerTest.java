package com.freidlin.dice.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.NestedServletException;

@SpringBootTest
@AutoConfigureMockMvc
class PlayControllerTest
{
  @Autowired
  private MockMvc _mockMvc;

  @Autowired
  private PlayController _playController;

  @Test
  public void contexLoads() throws Exception {
    assertThat(_playController).isNotNull();
  }

  @Test
  void testValidInputCorrectStatusReturned() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "1");
    inputParams.add("winChance", "45");
    inputParams.add("rollUnder", "true");

    _mockMvc.perform(get("/play").
      contentType("application/json").
      params(inputParams)
    ).andExpect(status().isOk());
  }

  @Test
  void testInvalidRequestMethod() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "1");
    inputParams.add("winChance", "45");
    inputParams.add("rollUnder", "true");

    _mockMvc.perform(post("/play").
      contentType("application/json").
      params(inputParams)
    ).andExpect(status().isMethodNotAllowed());
  }

  @Test
  void testInvalidWagerIsNull() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", null);
    inputParams.add("winChance", "45");
    inputParams.add("rollUnder", "true");

    _mockMvc.perform(get("/play").
      contentType("application/json").
      params(inputParams)
    ).andExpect(status().isBadRequest());
  }

  @Test
  void testInvalidWagerIsLessThanMin() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "0");
    inputParams.add("winChance", "45");
    inputParams.add("rollUnder", "true");
    assertThrows(NestedServletException.class, () -> {
      _mockMvc.perform(get("/play").
        contentType("application/json").
        params(inputParams)
      );
    });
  }

  @Test
  void testInvalidWagerIsOverThanMax() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "0");
    inputParams.add("winChance", "1001");
    inputParams.add("rollUnder", "true");
    assertThrows(NestedServletException.class, () -> {
      _mockMvc.perform(get("/play").
        contentType("application/json").
        params(inputParams)
      );
    });
  }

  @Test
  void testInvalidWinChanceIsNull() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "2");
    inputParams.add("winChance", null);
    inputParams.add("rollUnder", "true");

    _mockMvc.perform(get("/play").
      contentType("application/json").
      params(inputParams)
    ).andExpect(status().isBadRequest());
  }

  @Test
  void testInvalidWinChanceIsLessThanMin() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "1");
    inputParams.add("winChance", "1");
    inputParams.add("rollUnder", "true");
    assertThrows(NestedServletException.class, () -> {
      _mockMvc.perform(get("/play").
        contentType("application/json").
        params(inputParams)
      );
    });
  }

  @Test
  void testInvalidWinChanceIsOverThanMax() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "1");
    inputParams.add("winChance", "99");
    inputParams.add("rollUnder", "true");
    assertThrows(NestedServletException.class, () -> {
      _mockMvc.perform(get("/play").
        contentType("application/json").
        params(inputParams)
      );
    });
  }

  @Test
  void testInvalidRollUnderIsNull() throws Exception
  {
    LinkedMultiValueMap<String, String> inputParams = new LinkedMultiValueMap<>();

    inputParams.add("wager", "2");
    inputParams.add("winChance", "45");
    inputParams.add("rollUnder", null);

    _mockMvc.perform(get("/play").
      contentType("application/json").
      params(inputParams)
    ).andExpect(status().isBadRequest());
  }
}
