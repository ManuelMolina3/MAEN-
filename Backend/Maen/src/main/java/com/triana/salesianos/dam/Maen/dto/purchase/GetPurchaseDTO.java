package com.triana.salesianos.dam.Maen.dto.purchase;

import java.time.LocalDate;
import java.util.UUID;

public record GetPurchaseDTO (
        UUID idPurchase,
        LocalDate date,
        double total
){
}
