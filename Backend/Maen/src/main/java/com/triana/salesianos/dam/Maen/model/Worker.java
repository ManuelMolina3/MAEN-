package com.triana.salesianos.dam.Maen.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class Worker extends Usuario{

    private boolean isBoss;

    public Worker(UUID id, String username, String password, String email,
                  String name, boolean accountNonExpired,
                  boolean accountNonLocked, boolean credentialsNonExpired,
                  boolean enabled, LocalDateTime createdAt, LocalDateTime lastPasswordChangeAt,
                  boolean isBoss) {
        super(id, username, password, email, name, accountNonExpired, accountNonLocked,
                credentialsNonExpired, enabled, createdAt, lastPasswordChangeAt);
        this.isBoss = isBoss;
    }
}
