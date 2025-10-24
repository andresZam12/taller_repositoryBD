package com.ucc.repository;

import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepository implements IRepository {  // <— sin genéricos

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstanceConnection();
    }

    // ===== READ ALL =====
    @Override
    public List<Actor> findAll() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT actor_id, first_name, last_name FROM sakila.actor";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Actor a = new Actor();
                a.setActor_id(rs.getInt("actor_id"));
                a.setFirst_name(rs.getString("first_name"));
                a.setLast_name(rs.getString("last_name"));
                actors.add(a);
            }
        }
        return actors;
    }

    // ===== READ by ID =====
    @Override
    public Optional<Actor> findById(Integer id) throws SQLException {
        String sql = "SELECT actor_id, first_name, last_name FROM sakila.actor WHERE actor_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Actor a = new Actor();
                    a.setActor_id(rs.getInt("actor_id"));
                    a.setFirst_name(rs.getString("first_name"));
                    a.setLast_name(rs.getString("last_name"));
                    return Optional.of(a);
                }
            }
        }
        return Optional.empty();
    }

    // ===== CREATE =====
    @Override
    public Actor save(Actor actor) throws SQLException {
        // En sakila.actor el ID es AUTOINCREMENT; insertamos solo nombres y recuperamos la PK
        String sql = "INSERT INTO sakila.actor (first_name, last_name) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, actor.getFirst_name());
            st.setString(2, actor.getLast_name());
            st.executeUpdate();

            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) {
                    actor.setActor_id(keys.getInt(1));
                }
            }
        }
        return actor;
    }

    // ===== UPDATE =====
    @Override
    public boolean update(Actor actor) throws SQLException {
        String sql = "UPDATE sakila.actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, actor.getFirst_name());
            st.setString(2, actor.getLast_name());
            st.setInt(3, actor.getActor_id());

            int rows = st.executeUpdate();
            return rows == 1; // true si exactamente 1 fila fue actualizada
        }
    }

    // ===== DELETE =====
    @Override
    public boolean deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM sakila.actor WHERE actor_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            int rows = st.executeUpdate();
            return rows == 1; // true si exactamente 1 fila fue eliminada
        }
    }
}
