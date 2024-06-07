package com.triana.salesianos.dam.Maen.exception.electricityContract;

import jakarta.persistence.EntityNotFoundException;

public class ElectricityContractNotFoundException extends EntityNotFoundException {
    public ElectricityContractNotFoundException (){
        super("This electricity contract doesn´t exist");
    }
}
