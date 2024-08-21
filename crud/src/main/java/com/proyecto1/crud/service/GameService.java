package com.proyecto1.crud.service;

import com.proyecto1.crud.commons.entities.GameModel;

public interface GameService {
    GameModel createGame(GameModel gameModel);
    GameModel getGame(Long gameId);
    void deleteGame(Long gameId);
    void updateGame(GameModel gameModel, Long gameId);
}
