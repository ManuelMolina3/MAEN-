package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.electricityCompany.AddElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.exception.electricityCompany.ElectricityCompanyListEmptyException;
import com.triana.salesianos.dam.Maen.exception.electricityCompany.ElectricityCompanyNotDeleteException;
import com.triana.salesianos.dam.Maen.exception.electricityCompany.ElectricityCompanyNotFoundException;
import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.repository.ElectricityCompanyRepository;
import com.triana.salesianos.dam.Maen.repository.ElectricityContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ElectricityCompanyService {

    private final ElectricityCompanyRepository repository;
    private final ElectricityContractRepository electricityContractRepository;

    public Page<GetElectricityCompanyDTO> findAll (Pageable pageable){
        Page<GetElectricityCompanyDTO> ElectricityCompanyList = repository.findAllWithNumOfContract(pageable);

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
    public void delete (UUID idElectricityCompany){
        List<ElectricityContract> ElectricityContractByElectricityCompany = electricityContractRepository.findElectricityContractByElectricityCompany(idElectricityCompany);

        if(ElectricityContractByElectricityCompany.isEmpty())
            repository.deleteById(idElectricityCompany);
        else
            throw new ElectricityCompanyNotDeleteException();
    }
    public GetElectricityCompanyDTO edit (AddElectricityCompanyDTO edit, UUID idElectricityCompany){
        Optional<ElectricityCompany> ecFind = repository.findById(idElectricityCompany);

        if(ecFind.isPresent()){
            ecFind.get().setName(edit.name());
            ecFind.get().setLogotype(edit.logotype());
            repository.save(ecFind.get());

            return GetElectricityCompanyDTO.of(ecFind.get());
        }else{
            throw new ElectricityCompanyNotFoundException();
        }
    }

}
