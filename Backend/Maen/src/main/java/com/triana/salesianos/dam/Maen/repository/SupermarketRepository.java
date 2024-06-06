package com.triana.salesianos.dam.Maen.repository;

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
                sm.id, sm.name, sm.logoType,(
                    select case
                        when count(p) > 0 then count (p)
                        else 0
                    end
                    from Product p
                    where sm member of p.superMarket
                )
            )
            from Supermarket sm
            """
    )
    Page<SuperMarket> findAllWithNumOfProduct(Pageable pageable);
}
