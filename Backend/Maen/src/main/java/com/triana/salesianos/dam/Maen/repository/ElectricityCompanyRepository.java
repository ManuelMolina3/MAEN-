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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ElectricityCompanyRepository extends JpaRepository<ElectricityCompany, UUID> {


    @Query("""
    select e from ElectricityContract e
    where e.company.id = :companyId
    """)
    List<ElectricityContract> findElectricityContractByElectricityCompany(@Param("companyId") UUID companyId);

    @Query("""
            select new com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO(
                ec.id, ec.name, ec.logotype,(
                    select count(ect)
                        from ElectricityContract ect
                            where ect.company.id = ec.id
                )
            )
            from ElectricityCompany ec
            """)
    Page<GetElectricityCompanyDTO> findAllWithNumOfContract (Pageable pageable);

    @Query("""
            select ec from ElectricityCompany ec where ec.name = :name
            """)
    Optional<ElectricityCompany> findElectricityCompanyByName(@Param("name") String name);


}
