package com.triana.salesianos.dam.Maen.model;


import java.time.LocalDateTime;
import java.util.UUID;


public class Purchase {


    private UUID id_product;
    private UUID id_userMaen;

    private LocalDateTime date;

    private String concept;

    private double subtotal;

    private double total;

}
