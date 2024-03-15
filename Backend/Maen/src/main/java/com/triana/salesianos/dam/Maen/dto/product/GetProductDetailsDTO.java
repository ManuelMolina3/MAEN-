package com.triana.salesianos.dam.Maen.dto.product;

import com.triana.salesianos.dam.Maen.model.Product;
import lombok.Builder;

import java.util.UUID;
@Builder
public record GetProductDetailsDTO(
        UUID id,
        String productName,
        String productImage,
        String productBrand,

        double price,
        double priceKg,
        int taxes,
        String superMarketName,
        String superMarketImage

) {
    public static GetProductDetailsDTO of (Product p){
        return GetProductDetailsDTO.builder()
                .id(p.getId())
                .productName(p.getName())
                .productImage(p.getImage())
                .productBrand(p.getBrand())
                .price(p.getPrice())
                .priceKg(p.getPriceKg())
                .taxes(p.getTaxes())
                .superMarketName(p.getSuperMarket().getName())
                .superMarketImage(p.getSuperMarket().getLogotype())
                .build();
    }
}
