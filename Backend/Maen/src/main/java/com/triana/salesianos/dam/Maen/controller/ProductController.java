package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.product.AddProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDetailsDTO;
import com.triana.salesianos.dam.Maen.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "")
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<GetProductDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public GetProductDetailsDTO getProductById (@PathVariable UUID id){
        return service.getProductById(id);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<List<GetProductDTO>> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(service.getProductByName(name));
    }
    @GetMapping("/all")
    public MyPage<GetProductDTO> getAll (@PageableDefault(page = 0, size = 10)Pageable pageable){
        return MyPage.of(service.findAll(pageable).map(GetProductDTO::of));
    }
    @PostMapping("/")
    public ResponseEntity<GetProductDTO> createProduct (@RequestBody AddProductDTO nuevo){
        GetProductDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public GetProductDTO editProduct (@Valid @RequestBody AddProductDTO edit, @PathVariable UUID id){
        return service.edit(edit, id);
    }

}
