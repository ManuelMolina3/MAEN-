package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO;
import com.triana.salesianos.dam.Maen.model.SuperMarket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SupermarketRepository extends JpaRepository<SuperMarket, UUID> {

    @Query(
            """
            select new com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO(
                sm.id, sm.name, sm.logotype,(
                      select count(p)
                        from Product p
                            where p.superMarket.id = sm.id
                )
            )
            from SuperMarket sm
            """
    )
    Page<GetSupermarketDTO> findAllWithNumOfProduct(Pageable pageable);
}
