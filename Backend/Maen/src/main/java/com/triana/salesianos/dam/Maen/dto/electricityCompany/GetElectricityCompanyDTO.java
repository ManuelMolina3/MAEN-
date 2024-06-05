package com.triana.salesianos.dam.Maen.dto.electricityCompany;

import java.util.UUID;

public record GetElectricityCompanyDTO(
        UUID id,
        String name,
        String urlImage,
        int numOfContract
) {
}
