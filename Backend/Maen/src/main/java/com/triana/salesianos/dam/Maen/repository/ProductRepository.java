package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByName(String name);
    @Query("""
            select p
            from Product p
            where cast(p.id as string) = :id
            """)
    Optional<Product> findProductById(@Param("id") String id);

    @Query("SELECT COUNT(sl) FROM SalesLine sl WHERE sl.product.id = :productId")
    int findProductInSalesLine (@Param("productId") UUID productId);

    @Query("SELECT COUNT(p.superMarket) FROM Product p WHERE p.id = :productId")
    int findProductInSupermarket (@Param("productId") UUID productId);

    @Query("""
            Select sm.products from SuperMarket sm where sm.id = :idSupermarket
            """)
    List<Product> findProductBySupermarket(@Param("idSupermarket") UUID idSupermarket);
}
