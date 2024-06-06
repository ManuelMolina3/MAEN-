package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.electricityCompany.AddElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
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
        Page<ElectricityCompany> ElectricityCompanyList = repository.findAllWithNumOfContract(pageable);

        if(ElectricityCompanyList.isEmpty())
            throw new ElectricityCompanyListEmptyException();
        else
            return ElectricityCompanyList;
    }
    public GetElectricityCompanyDTO save (AddElectricityCompanyDTO nuevo){
        ElectricityCompany ec = new ElectricityCompany();
        ec.setName(nuevo.name());
        ec.setLogotype(nuevo.logotype());

        repository.save(ec);

        return GetElectricityCompanyDTO.of(ec);
    }
}
