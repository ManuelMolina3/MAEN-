package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.AddElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.service.ElectricityCompanyService;
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
@Tag(name = "Electricity Company", description = "")
@RequestMapping("/company")
public class ElectricityCompanyController {

    private final ElectricityCompanyService service;
    @GetMapping("/")
    public MyPage<GetElectricityCompanyDTO> getAll (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return MyPage.of(service.findAll(pageable));
    }

    @PostMapping("/")
    public ResponseEntity<GetElectricityCompanyDTO> createProduct (@RequestBody AddElectricityCompanyDTO nuevo){
        GetElectricityCompanyDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteElectricityCompany(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public GetElectricityCompanyDTO editElectricityCompany (@Valid @RequestBody AddElectricityCompanyDTO edit, @PathVariable UUID id){
        return service.edit(edit, id);
    }

}
