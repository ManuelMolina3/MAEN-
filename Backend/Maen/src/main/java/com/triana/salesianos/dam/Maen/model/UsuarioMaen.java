package com.triana.salesianos.dam.Maen.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioMaen extends Usuario{

    private double salary;

    private int numMembersOfFamily;

    public UsuarioMaen(UUID id, String username, String password, String email, String nombre,
                       boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
                       LocalDateTime createdAt, LocalDateTime lastPasswordChangeAt, double salary, int numMembersOfFamily) {
        super(id, username, password, email, nombre, accountNonExpired, accountNonLocked, credentialsNonExpired,
                enabled, createdAt, lastPasswordChangeAt);
        this.salary = salary;
        this.numMembersOfFamily = numMembersOfFamily;
    }
}
