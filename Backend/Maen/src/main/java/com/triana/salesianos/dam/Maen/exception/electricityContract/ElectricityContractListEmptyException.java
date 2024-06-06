package com.triana.salesianos.dam.Maen.exception.electricityContract;

public class ElectricityContractListEmptyException extends RuntimeException{
    public ElectricityContractListEmptyException(){
        super("This list doesn't have any electricity contract");
    }
}
