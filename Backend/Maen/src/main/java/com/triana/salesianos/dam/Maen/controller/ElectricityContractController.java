package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityContract.GetElectricityContractDTO;
import com.triana.salesianos.dam.Maen.service.ElectricityContractService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
