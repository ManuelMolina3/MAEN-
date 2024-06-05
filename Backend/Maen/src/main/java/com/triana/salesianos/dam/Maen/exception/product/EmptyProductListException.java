package com.triana.salesianos.dam.Maen.exception.product;

public class EmptyProductListException extends RuntimeException{
    public EmptyProductListException(){
        super("Don´t have product this list");
    }
}
