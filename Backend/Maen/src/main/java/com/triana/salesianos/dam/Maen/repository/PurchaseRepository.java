package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    @Query("SELECT * FROM Purchase WHERE date BETWEEN ? to ? AND idUserMaen = ?")
    Optional<List<Purchase>> getPurchasebyMonth(LocalDate from, LocalDate to, UUID idUserMaen);
}
