package com.triana.salesianos.dam.Maen.exception.product;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;


public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(){
        super("The product could not be found");
    }
    public ProductNotFoundException(UUID id){
        super(String.format("The product with the id %s could not be found", id));
    }
}
