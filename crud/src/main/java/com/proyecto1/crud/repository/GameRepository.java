package com.proyecto1.crud.repository;

import com.proyecto1.crud.commons.entities.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<GameModel,Long> {
}
