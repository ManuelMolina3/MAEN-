package com.triana.salesianos.dam.Maen.dto.product;

import com.triana.salesianos.dam.Maen.model.Category;
import com.triana.salesianos.dam.Maen.model.Product;
import lombok.Builder;

import java.util.UUID;
@Builder
public record GetProductDTO(
        UUID id,
        String productName,

        String productImage,

        String productBrand,

        double price,
        double taxes,
        String superMarketName,
        String superMarketImage


) {
    public static GetProductDTO of (Product p){
        return GetProductDTO.builder()
                .id(p.getId())
                .productName(p.getName())
                .productImage(p.getImage())
                .productBrand(p.getBrand())
                .price(p.getPrice())
                .taxes(p.getTaxes())
                .superMarketName(p.getSuperMarket().getName())
                .superMarketImage(p.getSuperMarket().getLogotype())
                .build();
    }
    public static GetProductDTO ofA (Product p){
        return GetProductDTO.builder()
                .id(p.getId())
                .productName(p.getName())
                .productImage(p.getImage())
                .productBrand(p.getBrand())
                .price(p.getPrice())
                .taxes(p.getTaxes())
                .superMarketName(p.getSuperMarket().getName())
                .build();
    }

}
