package com.triana.salesianos.dam.Maen.exception.supermarket;

public class SupermarketNotDeleteException extends RuntimeException{
    public SupermarketNotDeleteException(){
        super("This supermarket could be not delete because have many product");
    }
}
