package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDetailsDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.exception.product.EmptyProductListException;
import com.triana.salesianos.dam.Maen.model.Product;
import com.triana.salesianos.dam.Maen.repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository repository;

    public List<GetProductDTO> getAll (){
        return repository.findAll().stream().map(GetProductDTO::of).toList();
    }
    public GetProductDetailsDTO getProductById (UUID id){
        Optional<Product> productSelected = repository.findById(id);
        if(productSelected.isEmpty())
            throw new NotFoundException("Product");

        return GetProductDetailsDTO.of(productSelected.get());
    }
    public List<GetProductDTO> getProductByName(String name){
        Optional<List<Product>> productsSelected = repository.findByName(name);

        if (productsSelected.isEmpty()) {
            throw new NotFoundException("Product");
        }else{
            List<GetProductDTO> p = new ArrayList<>();
            for (int i = 0; i < productsSelected.get().size(); i++) {
                GetProductDTO g = GetProductDTO.of(productsSelected.get().get(i));
                p.add(g);
            }
            return p;
        }
        
    }
    public Page<Product> findAll (Pageable pageable){
        Page<Product> productList = repository.findAll(pageable);

        if(productList.isEmpty())
            throw new EmptyProductListException();
        else
            return productList;
    }
}
