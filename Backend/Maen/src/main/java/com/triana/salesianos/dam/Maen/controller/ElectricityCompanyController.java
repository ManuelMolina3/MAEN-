package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.AddElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.service.ElectricityCompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "Light Company", description = "")
public class ElectricityCompanyController {

    private final ElectricityCompanyService service;
    @GetMapping("/")
    public MyPage<GetElectricityCompanyDTO> getAll (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return MyPage.of(service.findAll(pageable).map(GetElectricityCompanyDTO::of));
    }

    @PostMapping("/")
    public ResponseEntity<GetElectricityCompanyDTO> createProduct (AddElectricityCompanyDTO nuevo){
        GetElectricityCompanyDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }


}
