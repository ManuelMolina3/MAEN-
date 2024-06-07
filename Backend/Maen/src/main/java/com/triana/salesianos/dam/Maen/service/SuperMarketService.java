package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.supermarket.AddSupermarketDTO;
import com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO;
import com.triana.salesianos.dam.Maen.exception.supermarket.SupermarketListEmptyException;
import com.triana.salesianos.dam.Maen.exception.supermarket.SupermarketNotDeleteException;
import com.triana.salesianos.dam.Maen.model.Product;
import com.triana.salesianos.dam.Maen.model.SuperMarket;
import com.triana.salesianos.dam.Maen.repository.ProductRepository;
import com.triana.salesianos.dam.Maen.repository.SupermarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SuperMarketService {

    private final SupermarketRepository repository;
    private final ProductRepository productRepository;

    public Page<SuperMarket> findAll (Pageable pageable){
        Page<SuperMarket> supermarketList = repository.findAllWithNumOfProduct(pageable);

        if(supermarketList.isEmpty())
            throw new SupermarketListEmptyException();
        else
            return supermarketList;

    }
    public GetSupermarketDTO save (AddSupermarketDTO nuevo){
        SuperMarket sm = new SuperMarket();
        sm.setName(nuevo.name());
        sm.setLogotype(nuevo.logotype());

        repository.save(sm);

        return GetSupermarketDTO.of(sm);
    }
    public void delete (UUID idSupermarket){
        List<Product> productBySupermarket = productRepository.findProductBySupermarket(idSupermarket);

        if(productBySupermarket.isEmpty())
            repository.deleteById(idSupermarket);
        else
            throw new SupermarketNotDeleteException();
    }
}
