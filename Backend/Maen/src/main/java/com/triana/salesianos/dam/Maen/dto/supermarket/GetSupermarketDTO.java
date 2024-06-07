package com.triana.salesianos.dam.Maen.dto.supermarket;

import com.triana.salesianos.dam.Maen.model.SuperMarket;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetSupermarketDTO(
        UUID id,
        String name,
        String logotype,
        Long numOfProduct
) {
    public static GetSupermarketDTO of (SuperMarket supermarket){
        return GetSupermarketDTO.builder()
                .id(supermarket.getId())
                .name(supermarket.getName())
                .logotype(supermarket.getLogotype())
                .numOfProduct(0L)
                .build();
    }
}
