package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElectricityContractRepository extends JpaRepository<ElectricityContract, UUID> {

    @Query("""
            Select ec.users from ElectricityContract ec where ec.id = :id
            """)
    List<UsuarioMaen> findAllUserHaveThisContract (@Param("id") UUID id);

    @Query("""
            select ec.contracts from ElectricityCompany ec where ec.id = :idElectricityCompany
            """)
    List<ElectricityContract> findElectricityContractByElectricityCompany(@Param("idElectricityCompany") UUID idElectricityCompany);
}
