package com.triana.salesianos.dam.Maen.dto.electricityContract;

public record AddElectricityContractDTO (
        double priceEnergy,
        int discountEnergy,

        double pricePower,
        double priceEquipment,
        int taxes,
        String nameCompany


){
}
