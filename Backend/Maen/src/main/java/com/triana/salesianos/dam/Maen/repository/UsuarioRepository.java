package com.triana.salesianos.dam.Maen.repository;

import com.triana.salesianos.dam.Maen.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByUsername(String username);
    boolean existsByUsernameIgnoreCase(String username);


}
