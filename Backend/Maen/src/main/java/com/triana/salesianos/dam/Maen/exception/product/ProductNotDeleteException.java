package com.triana.salesianos.dam.Maen.exception.product;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotDeleteException extends EntityNotFoundException {
    public ProductNotDeleteException(){
        super("This product con not be deleted");
    }
}
