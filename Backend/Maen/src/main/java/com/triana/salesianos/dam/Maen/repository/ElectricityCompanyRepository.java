package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ElectricityCompanyRepository extends JpaRepository<ElectricityCompany, UUID> {


    @Query("""
            SELECT e FROM ElectricityContract e
            WHERE e.company.id = :companyId
            """)
    Page<ElectricityContract> findElectricityContractByElectricityCompany(@Param("companyId") UUID companyId, Pageable pageable);

    @Query(
            """
            select new com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO(
                ec.id, ec.name, ec.logoType,(
                    select case
                        when count(ect) > 0 then count (ect)
                        else 0
                    end
                    from ElectricityContract ect
                    where ec member of ect.company
                )
            )
            from ElectricityCompany ec
            """
    )
    Page<ElectricityCompany> findAllWithNumOfContract (Pageable pageable);

    @Query("""
            Select ec from ElectricityCompany ec WHERE ec.name = :name
            """)
    Optional<ElectricityCompany> findElectricityCompanyByName(String name);


}
