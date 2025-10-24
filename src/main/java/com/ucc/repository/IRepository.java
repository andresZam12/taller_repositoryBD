package com.ucc.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.ucc.model.Actor;

public interface IRepository {
    List<Actor> findAll() throws SQLException;
    Optional<Actor> findById(Integer id) throws SQLException;

    Actor save(Actor actor) throws SQLException;      // CREATE
    boolean update(Actor actor) throws SQLException;  // UPDATE (true si afectó 1 fila)
    boolean deleteById(Integer id) throws SQLException; // DELETE (true si afectó 1 fila)
}
