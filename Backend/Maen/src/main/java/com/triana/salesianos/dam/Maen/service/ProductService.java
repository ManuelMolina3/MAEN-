package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.product.AddProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDetailsDTO;
import com.triana.salesianos.dam.Maen.exception.NotFoundException;
import com.triana.salesianos.dam.Maen.exception.product.EmptyProductListException;
import com.triana.salesianos.dam.Maen.exception.product.ProductInSalesLineException;
import com.triana.salesianos.dam.Maen.exception.product.ProductInSupermarketException;
import com.triana.salesianos.dam.Maen.exception.product.ProductNotFoundException;
import com.triana.salesianos.dam.Maen.exception.supermarket.SupermarketNotFoundException;
import com.triana.salesianos.dam.Maen.model.Category;
import com.triana.salesianos.dam.Maen.model.Product;
import com.triana.salesianos.dam.Maen.model.SuperMarket;
import com.triana.salesianos.dam.Maen.repository.ProductRepository;
import com.triana.salesianos.dam.Maen.repository.SupermarketRepository;
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
    private final SupermarketRepository superRepository;

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
        List<Product> productsSelected = repository.findByName(name);

        if (productsSelected.isEmpty()) {
            throw new NotFoundException("Product");
        }else{
            List<GetProductDTO> pList = new ArrayList<>();
            for (Product p : productsSelected) {
                pList.add(GetProductDTO.of(p));
            }
            return pList;
        }
        
    }
    public Page<Product> findAll (Pageable pageable){
        Page<Product> productList = repository.findAll(pageable);

        if(productList.isEmpty())
            throw new EmptyProductListException();
        else
            return productList;
    }
    public GetProductDTO save(AddProductDTO nuevo){
        Product p = new Product();
        p.setName(nuevo.name());
        p.setImage(nuevo.image());
        p.setBrand(nuevo.brand());
        p.setPrice(nuevo.price());
        p.setPriceKg(nuevo.priceKg());
        p.setTaxes(nuevo.taxes());

        Optional<SuperMarket> sm = superRepository.findById(nuevo.supermarketId());

        if(sm.isEmpty())
            throw new SupermarketNotFoundException();
        else
            p.setSuperMarket(sm.get());


        repository.save(p);
        return GetProductDTO.of(p);
    }
    public void delete (UUID id){
        int num = repository.findProductInSalesLine(id);
        int numSm = repository.findProductInSupermarket(id);

        if(num == 0){
            if(numSm == 0)
                repository.deleteById(id);
            else
                throw new ProductInSupermarketException();
        } else{
            throw new ProductInSalesLineException();
        }
    }
    public GetProductDTO edit (AddProductDTO editP, UUID id){
        Optional<Product> p = repository.findById(id);

        if(p.isEmpty()){
            throw new ProductNotFoundException();
        }else{
            Product edit = p.get();

            edit.setName(editP.name());
            edit.setImage(editP.image());
            edit.setBrand(editP.brand());
            edit.setPrice(editP.price());
            edit.setTaxes(editP.taxes());


            repository.save(edit);
            return GetProductDTO.of(edit);
        }
    }
}
