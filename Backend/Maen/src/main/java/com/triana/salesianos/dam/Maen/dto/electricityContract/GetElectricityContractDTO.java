package com.triana.salesianos.dam.Maen.dto.electricityContract;

import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import lombok.Builder;

import java.util.UUID;
@Builder
public record GetElectricityContractDTO(
        UUID id,
        double priceEnergy,
        double discountEnergy,

        double pricePower,
        double priceEquipment,
        double taxes,
        String companyName,
        String companyLogotype,
        UUID companyId
) {

    public static GetElectricityContractDTO of (ElectricityContract contract){
        return GetElectricityContractDTO.builder()
                .id(contract.getId())
                .priceEnergy(contract.getPriceEnergy())
                .discountEnergy(contract.getDiscountEnergy())
                .pricePower(contract.getPricePower())
                .priceEquipment(contract.getPriceEquipment())
                .taxes(contract.getTaxes())
                .companyName(contract.getCompany().getName())
                .companyLogotype(contract.getCompany().getLogotype())
                .companyId(contract.getCompany().getId())
                .build();
    }
    public static GetElectricityContractDTO ofA (ElectricityContract contract){
        return GetElectricityContractDTO.builder()
                .id(contract.getId())
                .priceEnergy(contract.getPriceEnergy())
                .discountEnergy(contract.getDiscountEnergy())
                .pricePower(contract.getPricePower())
                .priceEquipment(contract.getPriceEquipment())
                .taxes(contract.getTaxes())
                .companyName(contract.getCompany().getName())
                .build();
    }
}
