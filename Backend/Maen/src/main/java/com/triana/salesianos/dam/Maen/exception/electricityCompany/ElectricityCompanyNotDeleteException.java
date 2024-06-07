package com.triana.salesianos.dam.Maen.exception.electricityCompany;

public class ElectricityCompanyNotDeleteException extends RuntimeException{
    public ElectricityCompanyNotDeleteException(){
        super("Could be not delete this company because has many contract");
    }
}
