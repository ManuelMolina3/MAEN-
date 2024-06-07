package com.triana.salesianos.dam.Maen.exception.supermarket;

import jakarta.persistence.EntityNotFoundException;

public class SupermarketNotFoundException extends EntityNotFoundException {
    public SupermarketNotFoundException(){
        super("The supermarket could not be found");
    }
}

