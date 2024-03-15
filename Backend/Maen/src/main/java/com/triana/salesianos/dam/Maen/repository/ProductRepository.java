package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByName(String name,
                             Pageable pageable);

}
