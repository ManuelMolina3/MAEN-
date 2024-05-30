package com.triana.salesianos.dam.Maen.dto.salesLine;

import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.model.SalesLine;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetSalesLineDTO(
        UUID idSalesLine,
        int amount,
        GetProductDTO product,
        double subTotal

) {
    public static GetSalesLineDTO of (SalesLine sl){
        return GetSalesLineDTO.builder()
                .idSalesLine(sl.getIdSalesLine())
                .amount(sl.getAmount())
                .product(GetProductDTO.of(sl.getProduct()))
                .subTotal(sl.getSubTotal())
                .build();
    }
}
