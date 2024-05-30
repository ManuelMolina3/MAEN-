package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    @Query("SELECT p FROM Purchase p WHERE p.date BETWEEN :from AND :to AND p.idUserMaen = :idUserMaen")
    List<Purchase> getPurchasebyMonth(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("idUserMaen") UUID idUserMaen);

    @Query("Select p FROM Purchase p WHERE date = :date")
    Purchase getPurchaseByDate(@Param("date") LocalDate date);
}
