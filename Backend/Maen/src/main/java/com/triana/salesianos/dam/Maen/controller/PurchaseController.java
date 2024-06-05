package com.triana.salesianos.dam.Maen.controller;


import com.triana.salesianos.dam.Maen.dto.purchase.GetPurchaseDetailsDTO;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import com.triana.salesianos.dam.Maen.service.PurchaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
@Tag(name = "Purchase", description = "This is the controller where make the methods for Purchase class and SalesLine class")
public class PurchaseController {

    private final PurchaseService service;

    public GetPurchaseDetailsDTO addProductToPurchase (@PathVariable String id, @AuthenticationPrincipal UsuarioMaen u, LocalDate date){
        return service.getPurchaseDetailsById(service.addProductToPurchase(id, u, date).getIdPurchase());
    }

}
