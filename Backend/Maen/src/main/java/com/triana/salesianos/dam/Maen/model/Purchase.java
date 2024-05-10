package com.triana.salesianos.dam.Maen.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID idPurchase;

    private UUID idUserMaen;

    private LocalDateTime date;

    private double total;

    @OneToMany
    private List<SalesLine> salesLineList;
}
