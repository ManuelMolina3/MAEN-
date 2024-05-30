package com.triana.salesianos.dam.Maen.dto.purchase;

import com.triana.salesianos.dam.Maen.dto.salesLine.GetSalesLineDTO;
import com.triana.salesianos.dam.Maen.model.Purchase;
import com.triana.salesianos.dam.Maen.model.SalesLine;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record GetPurchaseDetailsDTO(
        UUID idPurchase,
        LocalDate date,
        double total,
        List<GetSalesLineDTO> salesLines
) {
    public static GetPurchaseDetailsDTO of (Purchase p, List<SalesLine> gsl){
        return GetPurchaseDetailsDTO.builder()
                .idPurchase(p.getIdPurchase())
                .date(p.getDate())
                .total(p.getTotal())
                .salesLines(gsl.stream().map(GetSalesLineDTO::of).toList())
                .build();

    }
}
