package com.triana.salesianos.dam.Maen.dto.salesLine;

import lombok.Builder;

import java.util.UUID;
@Builder
public record AddSalesLineDTO(
        UUID idUser,
        String productID,
        int amount
) {


}
