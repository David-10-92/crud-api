package com.proyecto1.crud.controller;

import com.proyecto1.crud.commons.entities.GameModel;
import com.proyecto1.crud.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi{

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<GameModel> createGame(GameModel gameRequest) {
        return ResponseEntity.ok(gameService.createGame(gameRequest));
    }

    @Override
    public ResponseEntity<GameModel> getGame(Long gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    @Override
    public ResponseEntity<Void> deteleGame(long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateGame(GameModel gameRequest, Long gameId) {
        gameService.updateGame(gameRequest,gameId);
        return ResponseEntity.noContent().build();
    }
}
