package com.triana.salesianos.dam.Maen.dto.supermarket;

import java.util.UUID;

public record GetSupermarketDTO(
        UUID id,
        String name,
        String logotype,
        int numOfProduct
) {
}
