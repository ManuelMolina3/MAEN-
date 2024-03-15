package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.SuperMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SupermarketRepository extends JpaRepository<SuperMarket, UUID> {
}
