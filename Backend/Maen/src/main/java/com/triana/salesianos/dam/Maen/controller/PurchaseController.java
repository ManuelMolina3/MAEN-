package com.triana.salesianos.dam.Maen.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
@Tag(name = "Purchase", description = "This is the controller where make the methods for Purchase class and SalesLine class")
public class PurchaseController {
}
