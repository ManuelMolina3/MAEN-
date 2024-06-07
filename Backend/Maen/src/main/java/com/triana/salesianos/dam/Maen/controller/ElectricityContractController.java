package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityContract.AddElectricityContractDTO;
import com.triana.salesianos.dam.Maen.dto.electricityContract.GetElectricityContractDTO;
import com.triana.salesianos.dam.Maen.service.ElectricityContractService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
@Tag(name = "Electricity contract", description = "")
public class ElectricityContractController {

    private final ElectricityContractService service;

    @GetMapping("/")
    public MyPage<GetElectricityContractDTO> getAll(@PageableDefault(size = 12, page = 0) Pageable pageable){
        return service.getAll(pageable);
    }
    @GetMapping("/{id}")
    public GetElectricityContractDTO getContractById (@PathVariable UUID id){
        return service.getContractById(id);
    }

    @GetMapping("/company/{companyId}")
    public MyPage<GetElectricityContractDTO> getContractByCompany (@PathVariable UUID companyId, @PageableDefault (page = 0, size = 10) Pageable pageable){
        return service.getContractByCompany(companyId, pageable);
    }
    @GetMapping("/all")
    public MyPage<GetElectricityContractDTO> getAllA (@PageableDefault(page = 0, size = 10)Pageable pageable){
       return MyPage.of(service.findAll(pageable).map(GetElectricityContractDTO::of));
    }
    @PostMapping("/")
    public ResponseEntity<GetElectricityContractDTO> createElectricityContract (AddElectricityContractDTO nuevo){
        GetElectricityContractDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteElectricityContract(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
