package com.proyecto1.crud.controller;

import com.proyecto1.crud.commons.entities.GameModel;
import com.proyecto1.crud.commons.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.GAME_ROUTER + ApiPathConstants.V1_ROUTER)
public interface GameApi {

    @PostMapping(value = "/create")
    ResponseEntity<GameModel> createGame(@RequestBody GameModel gameRequest);
    @GetMapping(value = "/{gameId}")
    ResponseEntity<GameModel> getGame(@PathVariable Long gameId);
    @DeleteMapping(value = "/{gameId}")
    ResponseEntity<Void> deteleGame(@PathVariable long gameId);
    @PutMapping(value = "/{gameId}")
    ResponseEntity<Void> updateGame(@RequestBody GameModel gameRequest,@PathVariable Long gameId);


}
