package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityContract.AddElectricityContractDTO;
import com.triana.salesianos.dam.Maen.dto.electricityContract.GetElectricityContractDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.exception.electricityCompany.ElectricityCompanyNotFoundException;
import com.triana.salesianos.dam.Maen.exception.electricityContract.ContractNotDeleteException;
import com.triana.salesianos.dam.Maen.exception.electricityContract.ElectricityContractListEmptyException;
import com.triana.salesianos.dam.Maen.exception.electricityContract.ElectricityContractNotFoundException;
import com.triana.salesianos.dam.Maen.model.ElectricityCompany;
import com.triana.salesianos.dam.Maen.model.ElectricityContract;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import com.triana.salesianos.dam.Maen.repository.ElectricityContractRepository;
import com.triana.salesianos.dam.Maen.repository.ElectricityCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<GetElectricityContractDTO> getContractByCompany(UUID companyId){
        List<ElectricityContract> contractList = companyRepository.findElectricityContractByElectricityCompany(companyId);

        if (contractList.isEmpty()){
            throw new NotFoundException("Contract");
        }else{
            List<GetElectricityContractDTO> contractListDTO = new ArrayList<>();
            for (ElectricityContract x : contractList) {
                contractListDTO.add(GetElectricityContractDTO.of(x));
            }
            return contractListDTO;
        }
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
    public void delete (UUID idContract){
        List<UsuarioMaen> ContractWithUser = repository.findAllUserHaveThisContract(idContract);

        if(ContractWithUser.isEmpty())
            repository.deleteById(idContract);
        else
            throw new ContractNotDeleteException();


    }
    public GetElectricityContractDTO edit (AddElectricityContractDTO editEct, UUID id){
        Optional<ElectricityContract> ect = repository.findById(id);

        if(ect.isEmpty()){
            throw new ElectricityContractNotFoundException();
        }else{
            ElectricityContract edit = ect.get();

            edit.setPriceEnergy(editEct.priceEnergy());
            edit.setDiscountEnergy(editEct.discountEnergy());
            edit.setPricePower(editEct.pricePower());
            edit.setPriceEquipment(editEct.priceEquipment());
            edit.setTaxes(editEct.taxes());

            repository.save(edit);
            return GetElectricityContractDTO.of(edit);
        }
    }
}
