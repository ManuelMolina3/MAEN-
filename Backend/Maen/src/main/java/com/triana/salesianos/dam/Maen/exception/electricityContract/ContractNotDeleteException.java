package com.triana.salesianos.dam.Maen.exception.electricityContract;

public class ContractNotDeleteException extends RuntimeException{
    public ContractNotDeleteException(){
        super("This contract could not be delete because is associated with many users");
    }

}
