package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<List<Product>> findByName(String name);
    @Query("""
            select p
            from Producto p
            where cast(p.id as string)=?1
            """)
    Optional<Product> findProductById(String id);

    @Query("SELECT COUNT(sl) FROM SalesLine sl WHERE lp.product.id = ?1")
    int findProductInSalesLine (UUID productId);

    @Query("SELECT COUNT(sm) FROM SuperMarket sm WHERE sm.product.id = ?1")
    int findProductInSUpermarket (UUID productId);

    @Query("""
            Select sm.product from SuperMarket sm where sm.id = ?1
            """)
    List<Product> findProductBySupermarket(UUID idSupermarket);
}
