package com.triana.salesianos.dam.Maen.dto.salesLine;

import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;

import java.util.UUID;

public record GetSalesLineDTO(
        UUID idSalesLine,
        int amount,
        GetProductDTO product,
        double subTotal

) {
}
