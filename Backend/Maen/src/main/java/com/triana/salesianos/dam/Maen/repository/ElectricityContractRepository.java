package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElectricityContractRepository extends JpaRepository<ElectricityContract, UUID> {

    @Query("""
            Select ec.usuarioMaen from ElectricityContract ec where ec.id = ?1
            """)
    List<UsuarioMaen> findAllUserHaveThisContract (UUID id);

    @Query("""
            Select ec.electricityContract from ElectricityCompany ec WHERE ec.id ?1
            """)
    List<ElectricityContract> findElectricityContractByElectricityCompany(UUID idElectricityCompany);
}
