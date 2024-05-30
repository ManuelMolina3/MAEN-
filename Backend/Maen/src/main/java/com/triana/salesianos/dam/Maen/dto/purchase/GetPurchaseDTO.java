package com.triana.salesianos.dam.Maen.dto.purchase;

import com.triana.salesianos.dam.Maen.model.Purchase;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Builder
public record GetPurchaseDTO (
        UUID idPurchase,
        LocalDate date,
        double total
){

    public static GetPurchaseDTO of (Purchase p){
        return GetPurchaseDTO.builder()
                .idPurchase(p.getIdPurchase())
                .date(p.getDate())
                .total(p.getTotal())
                .build();

    }
}
