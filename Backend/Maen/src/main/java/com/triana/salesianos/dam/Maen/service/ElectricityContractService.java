package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityContract.AddElectricityContractDTO;
import com.triana.salesianos.dam.Maen.dto.electricityContract.GetElectricityContractDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.exception.electricityCompany.ElectricityCompanyNotFoundException;
import com.triana.salesianos.dam.Maen.exception.electricityContract.ElectricityContractListEmptyException;
import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.repository.ElectricityContractRepository;
import com.triana.salesianos.dam.Maen.repository.ElectricityCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ElectricityContractService {

    private final ElectricityContractRepository repository;
    private final ElectricityCompanyRepository companyRepository;

    public MyPage<GetElectricityContractDTO> getAll(Pageable pageable){
        Page<ElectricityContract> contractList= repository.findAll(pageable);

        if (contractList.isEmpty())
            throw new NotFoundException("Contract");

        return MyPage.of(contractList.map(GetElectricityContractDTO::of));
    }
    public GetElectricityContractDTO getContractById (UUID id){
        Optional<ElectricityContract> contractSelected = repository.findById(id);
        if(contractSelected.isEmpty())
            throw new NotFoundException("Contract");

        return GetElectricityContractDTO.of(contractSelected.get());
    }
    public MyPage<GetElectricityContractDTO> getContractByCompany(UUID companyId, Pageable pageable){
        Page<ElectricityContract> result = companyRepository.findElectricityContractByElectricityCompany(companyId, pageable);

        if (result.isEmpty())
            throw new NotFoundException("Contract");

        return MyPage.of(result.map(GetElectricityContractDTO::of));
    }
    public Page<ElectricityContract> findAll (Pageable pageable){
        Page<ElectricityContract> electricityContractList = repository.findAll(pageable);

        if(electricityContractList.isEmpty())
            throw new ElectricityContractListEmptyException();
        else
            return electricityContractList;
    }
    public GetElectricityContractDTO save(AddElectricityContractDTO nuevo){
        ElectricityContract ect = new ElectricityContract();
        ect.setPriceEnergy(nuevo.priceEnergy());
        ect.setDiscountEnergy(nuevo.discountEnergy());
        ect.setPricePower(nuevo.pricePower());
        ect.setPriceEquipment(nuevo.priceEquipment());
        ect.setTaxes(nuevo.taxes());

        Optional<ElectricityCompany> ec = companyRepository.findElectricityCompanyByName(nuevo.nameCompany());
        if(ec.isEmpty())
            throw new ElectricityCompanyNotFoundException();
        else
            ect.setCompany(ec.get());


        repository.save(ect);
        return GetElectricityContractDTO.of(ect);
    }
}
