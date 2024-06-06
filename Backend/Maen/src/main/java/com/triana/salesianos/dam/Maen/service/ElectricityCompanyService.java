package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.exception.electricityCompany.ElectricityCompanyListEmptyException;
import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import com.triana.salesianos.dam.Maen.repository.ElectricityCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectricityCompanyService {

    private final ElectricityCompanyRepository repository;

    public Page<ElectricityCompany> findAll (Pageable pageable){
        Page<ElectricityCompany> ElectricityCompanyList = repository.findAll(pageable);

        if(ElectricityCompanyList.isEmpty())
            throw new ElectricityCompanyListEmptyException();
        else
            return ElectricityCompanyList;
    }
}
