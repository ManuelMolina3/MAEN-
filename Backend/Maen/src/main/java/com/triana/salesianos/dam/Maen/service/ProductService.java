package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDetailsDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.model.Product;
import com.triana.salesianos.dam.Maen.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public MyPage<GetProductDTO> getAll (Pageable pageable){
    Page<Product> productList = repository.findAll(pageable);

    if(productList.isEmpty())
        throw new NotFoundException("Product");


    return MyPage.of(productList.map(GetProductDTO::of));

    }
    public GetProductDetailsDTO getProductById (UUID id){
        Optional<Product> productSelected = repository.findById(id);
        if(productSelected.isEmpty())
            throw new NotFoundException("Product");

        return GetProductDetailsDTO.of(productSelected.get());
    }
    public MyPage<GetProductDTO> getProductByName(String name, Pageable pageable){
        Page<Product> productsSelected = repository.findByName(name, pageable);

        if (productsSelected.isEmpty())
            throw new NotFoundException("Product");

        return MyPage.of(productsSelected.map(GetProductDTO::of));


    }
}
