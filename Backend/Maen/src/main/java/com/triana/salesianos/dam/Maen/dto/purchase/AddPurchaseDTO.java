package com.triana.salesianos.dam.Maen.dto.purchase;

import java.time.LocalDate;
import java.util.List;

public record AddPurchaseDTO(
        LocalDate date,
        List<String> idSalesLine,

        double total,
        String idUserMaen


) {
}
