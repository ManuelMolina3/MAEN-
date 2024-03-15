package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, UUID> {


    Optional<Worker> findByName(String name);
}
