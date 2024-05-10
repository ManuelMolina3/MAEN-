package com.triana.salesianos.dam.Maen.dto.purchase;

import com.triana.salesianos.dam.Maen.dto.salesLine.GetSalesLineDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record GetPurchaseDetailsDTO(
        UUID idPurchase,
        LocalDate date,
        double total,
        List<GetSalesLineDTO> salesLines
) {
}
