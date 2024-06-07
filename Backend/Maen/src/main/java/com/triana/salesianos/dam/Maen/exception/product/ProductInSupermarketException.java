package com.triana.salesianos.dam.Maen.exception.product;

public class ProductInSupermarketException extends RuntimeException{
    public ProductInSupermarketException(){
        super("This product could not be delete because is in supermarket");
    }

}
