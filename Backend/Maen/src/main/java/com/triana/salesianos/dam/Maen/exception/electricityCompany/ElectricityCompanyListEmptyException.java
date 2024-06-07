package com.triana.salesianos.dam.Maen.exception.electricityCompany;

public class ElectricityCompanyListEmptyException extends RuntimeException{
    public ElectricityCompanyListEmptyException(){
        super("Don´t has any electricity companies");
    }
}
