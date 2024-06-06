package com.triana.salesianos.dam.Maen.exception.supermarket;

public class SupermarketListEmptyException extends RuntimeException{
    public SupermarketListEmptyException(){
        super("Don´t has supermarket this list");
    }
}
