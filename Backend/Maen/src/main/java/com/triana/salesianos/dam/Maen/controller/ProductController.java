package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDetailsDTO;
import com.triana.salesianos.dam.Maen.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "")
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<GetProductDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public GetProductDetailsDTO getProductById (@PathVariable UUID id){
        return service.getProductById(id);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<List<GetProductDTO>> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(service.getProductByName(name));
    }
}
