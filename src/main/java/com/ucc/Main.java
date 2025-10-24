package com.ucc;

import java.sql.Connection;
import java.sql.SQLException;

import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;
import com.ucc.repository.ActorRepository;
import com.ucc.repository.IRepository;

public class Main {

    public static void main(String[] args) {
        // Abrimos la conexión solo para verificar que la BD responde.
        // El repositorio abre/cierra su propia conexión en cada operación.
        try (Connection conn = DatabaseConnection.getInstanceConnection()) {
            System.out.println("Conexión OK");

            IRepository actorRepository = new ActorRepository();

            // ===== CREATE =====
            // No seteamos actor_id porque en sakila.actor es AUTO_INCREMENT
            Actor nuevo = new Actor();
            nuevo.setFirst_name("PepitoCode2");
            nuevo.setLast_name("pepitoCode2");

            actorRepository.save(nuevo);
            System.out.println("Actor creado con id: " + nuevo.getActor_id());

            // ===== READ (ALL) =====
            System.out.println("=== Lista de actores (parcial) ===");
            actorRepository.findAll()
                           .stream()
                           .limit(5) // para no imprimir toda la tabla si es grande
                           .forEach(System.out::println);

            // ===== UPDATE =====
            nuevo.setFirst_name("PepitoUpdated");
            nuevo.setLast_name("PepitoUpdated");
            boolean okUpdate = actorRepository.update(nuevo);
            System.out.println("Actualizado? " + okUpdate);

            // (Opcional) READ por id para verificar
            actorRepository.findById(nuevo.getActor_id())
                           .ifPresent(a -> System.out.println("Post-update: " + a));

            // ===== DELETE =====
            boolean okDelete = actorRepository.deleteById(nuevo.getActor_id());
            System.out.println("Eliminado? " + okDelete);

        } catch (SQLException e) {
            System.out.println("Error SQL:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Conexión Fail");
            e.printStackTrace();
        }
    }
}
