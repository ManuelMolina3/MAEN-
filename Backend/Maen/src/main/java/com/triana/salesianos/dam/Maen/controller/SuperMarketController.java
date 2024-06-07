package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.supermarket.AddSupermarketDTO;
import com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO;
import com.triana.salesianos.dam.Maen.service.SuperMarketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "SuperMarket", description = "")
@RequestMapping("/supermarket")
public class SuperMarketController {

    private final SuperMarketService service;

    @GetMapping("/")
    public MyPage<GetSupermarketDTO> getAll (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return MyPage.of((service.findAll(pageable)));
    }
    @PostMapping("/")
    public ResponseEntity<GetSupermarketDTO> createdSupermarket (AddSupermarketDTO nuevo){
        GetSupermarketDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @DeleteMapping("/")
    public ResponseEntity<?> deleteSupermarket(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public GetSupermarketDTO editSupermarket (@Valid @RequestBody AddSupermarketDTO edit, @PathVariable UUID id){
        return service.edit(edit, id);
    }
}
