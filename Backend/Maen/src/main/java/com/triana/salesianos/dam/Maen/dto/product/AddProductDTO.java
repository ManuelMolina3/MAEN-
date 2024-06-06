package com.triana.salesianos.dam.Maen.dto.product;

import com.triana.salesianos.dam.Maen.model.Category;
import com.triana.salesianos.dam.Maen.model.SuperMarket;

public record AddProductDTO(
        String name,
        String image,
        String brand,
        double price,
        double priceKg,
        int taxes,
        Category category,
        SuperMarket supermarket
) {
}
