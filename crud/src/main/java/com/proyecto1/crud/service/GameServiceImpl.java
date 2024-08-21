package com.proyecto1.crud.service;

import com.proyecto1.crud.commons.entities.GameModel;
import com.proyecto1.crud.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel createGame(GameModel gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error al crear el juego"));
    }

    @Override
    public GameModel getGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el juego con id = " + gameId));
    }

    @Override
    public GameModel deleteGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .map(game -> {gameRepository.delete(game); return game;})
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el juego con id = " + gameId));
    }

    @Override
    public GameModel updateGame(GameModel gameModel, Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .map(game -> {game.setName(gameModel.getName());
                    return game;
                })
                .map(gameRepository::save)
                .orElseThrow(()-> new RuntimeException("No se ha encontrado el juego con id = " + gameId));
    }

    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder()
                .name(gameRequest.getName())
                .build();
    }
}
