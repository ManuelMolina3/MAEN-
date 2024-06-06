package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ElectricityCompanyRepository extends JpaRepository<ElectricityCompany, UUID> {


    @Query("""
            SELECT e FROM ElectricityContract e
            WHERE e.company.id = :companyId
            """)
    Page<ElectricityContract> findElectricityContractByElectricityCompany(@Param("companyId") UUID companyId, Pageable pageable);
}
