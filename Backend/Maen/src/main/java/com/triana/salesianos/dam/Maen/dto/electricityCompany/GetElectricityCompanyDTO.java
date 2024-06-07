package com.triana.salesianos.dam.Maen.dto.electricityCompany;

import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetElectricityCompanyDTO(
        UUID id,
        String name,
        String urlImage,
        Long numOfContract
) {
    public static GetElectricityCompanyDTO of(ElectricityCompany ec){
        return GetElectricityCompanyDTO.builder()
                .id(ec.getId())
                .name(ec.getName())
                .urlImage(ec.getLogotype())
                .numOfContract(0L)
                .build();
    }
}
