package com.triana.salesianos.dam.Maen.exception.product;

public class ProductInSalesLineException extends RuntimeException{
    public ProductInSalesLineException(){
        super("This product could not be delete because is in sales line");
    }
}
