package com.proyecto1.crud.service.impl;

import com.proyecto1.crud.commons.entities.GameModel;
import com.proyecto1.crud.repository.GameRepository;
import com.proyecto1.crud.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel createGame(GameModel gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error creating the game"));
    }

    @Override
    public GameModel getGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .orElseThrow(() -> new RuntimeException("Game not found with id = " + gameId));
    }

    @Override
    public void deleteGame(Long gameId) {
        Optional.of(gameId)
                .map(this::getGameByGameId)
                .ifPresent(gameRepository::delete);
    }

    private GameModel getGameByGameId(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(()-> new RuntimeException("Game not found with id = " + gameId));
    }

    @Override
    public void updateGame(GameModel gameUpdateRequest, Long gameId) {
        Optional.of(gameId)
                .map(this::getGameByGameId)
                .map(existingGame -> updateFieldsGame(existingGame,gameUpdateRequest))
                .map(gameRepository::save)
                .orElseThrow(()-> new RuntimeException("Game not found with id = " + gameId));
    }

    private GameModel updateFieldsGame(GameModel existingGame, GameModel gameUpdateRequest) {
        existingGame.setName(gameUpdateRequest.getName());
        return existingGame;
    }

    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder()
                .name(gameRequest.getName())
                .build();
    }
}
