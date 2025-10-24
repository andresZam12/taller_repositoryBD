package com.ucc;

import java.sql.Connection;
import java.sql.SQLException;

import com.ucc.connection.DatabaseConnection;
import com.ucc.model.Actor;
import com.ucc.repository.ActorRepository;
import com.ucc.repository.IRepository;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getInstanceConnection()) {
            System.out.println("Conexión OK");

            IRepository actorRepository = new ActorRepository();


            //create
            Actor nuevo = new Actor();
            nuevo.setFirst_name("PepitoCode2");
            nuevo.setLast_name("pepitoCode2");

            actorRepository.save(nuevo);
            System.out.println("Actor creado con id: " + nuevo.getActor_id());

            //lectura de tdo
            System.out.println("=== Lista de actores (parcial) ===");
            actorRepository.findAll()
                           .stream()
                           .limit(5) 
                           .forEach(System.out::println);

            //update
            nuevo.setFirst_name("PepitoUpdated");
            nuevo.setLast_name("PepitoUpdated");
            boolean okUpdate = actorRepository.update(nuevo);
            System.out.println("Actualizado? " + okUpdate);

            
            actorRepository.findById(nuevo.getActor_id())
                           .ifPresent(a -> System.out.println("Post-update: " + a));

            //delete
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
