package com.triana.salesianos.dam.Maen.exception.electricityCompany;

import jakarta.persistence.EntityNotFoundException;

public class ElectricityCompanyNotFoundException extends EntityNotFoundException {
    public ElectricityCompanyNotFoundException(){
        super("The company could not be found");
    }
}
