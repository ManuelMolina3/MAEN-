package com.triana.salesianos.dam.Maen.model;


import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class Purchase {

    private UUID idPurchase;

    private UUID idUserMaen;

    private LocalDateTime date;

    private double total;

    @OneToMany
    private List<SalesLine> salesLineList;
}
