package com.triana.salesianos.dam.Maen.model;

import jakarta.persistence.OneToOne;

import java.util.UUID;

public class SalesLine {

    private UUID idSalesLine;

    private int amount;

    private double subTotal;

    @OneToOne
    private Product product;
}
