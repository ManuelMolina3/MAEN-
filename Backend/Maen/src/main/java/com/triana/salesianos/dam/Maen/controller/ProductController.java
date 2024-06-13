package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityContract.GetElectricityContractDTO;
import com.triana.salesianos.dam.Maen.dto.product.AddProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDTO;
import com.triana.salesianos.dam.Maen.dto.product.GetProductDetailsDTO;
import com.triana.salesianos.dam.Maen.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "Product", description = "This is the controller when operates all methods of product entity")
public class ProductController {

    private final ProductService service;

    @Operation(summary = "getAll", description = "Find all product in the database for angular application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The product has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetProductDTO.class)), examples = {
                            @ExampleObject(value = """
                                    [
                                         {
                                             "id": "ba9f2f92-5c4d-49ce-8f01-ee073447bfb8",
                                             "productName": "tomato",
                                             "productImage": "https://prod-mercadona.imgix.net/images/d9b74d242c05f180baf1f2af25255867.jpg",
                                             "productBrand": "hacendado",
                                             "price": 1.03,
                                             "taxes": 21.0,
                                             "superMarketName": "Mercadona",
                                             "superMarketImage": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png"
                                         },
                                         {
                                             "id": "995e94d3-c39e-4422-9ae6-ff11aaab8ab2",
                                             "productName": "tomato",
                                             "productImage": "https://a0.soysuper.com/4f8b1a659801347bd2c1b8bd469910f1.1500.0.0.0.wmark.9d3c4d01.jpg",
                                             "productBrand": "classic",
                                             "price": 1.01,
                                             "taxes": 21.0,
                                             "superMarketName": "Carrefour",
                                             "superMarketImage": "https://static.carrefour.es/crs/cdn_static/c4corp-front/images/logos/og/logo-carrefour-og_3.jpg"
                                         },
                                         {
                                             "id": "da98fe64-3c82-4758-bc3d-1dc183fb2a7b",
                                             "productName": "tomato",
                                             "productImage": "https://www.dia.es/product_images/10677/10677_ISO_0_ES.jpg",
                                             "productBrand": "vegecampo",
                                             "price": 1.4,
                                             "taxes": 21.0,
                                             "superMarketName": "MAS",
                                             "superMarketImage": "https://blog.supermercadosmas.com/wp-content/uploads/2018/01/blog-prueba23.jpg"
                                         },
                                         {
                                             "id": "0b238cc4-e9b7-4b3a-8da0-822cd3dfe40d",
                                             "productName": "tomato",
                                             "productImage": "https://www.lidl.es/media/product/0/0/1/4/7/8/3/tomate-frito-con-cebolla-zoom--2.jpg",
                                             "productBrand": "freshona",
                                             "price": 1.23,
                                             "taxes": 21.0,
                                             "superMarketName": "ALDI",
                                             "superMarketImage": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/ALDI_Nord_Logo_2015.png/731px-ALDI_Nord_Logo_2015.png"
                                         },
                                         {
                                             "id": "fc1e37eb-2db4-456d-9d9d-8645bd14d626",
                                             "productName": "tomato",
                                             "productImage": "https://www.auchan.pt/dw/image/v2/BFRC_PRD/on/demandware.static/-/Sites-auchan-pt-master-catalog/default/dw0678a7a4/images/hi-res/002474035.jpg",
                                             "productBrand": "auchan",
                                             "price": 0.9,
                                             "taxes": 21.0,
                                             "superMarketName": "Cash Fre",
                                             "superMarketImage": "https://www.cashfres/wp-content/uploads/2020/02/CASH-FRESH.jpg"
                                         },
                                         {
                                             "id": "448bde02-d045-4d47-a912-40123f2665ad",
                                             "productName": "tomato",
                                             "productImage": "https://www.clara.es/medio/2021/02/22/tomate-frito-ferrer_477a097f_1000x1500.jpg",
                                             "productBrand": "ferrer",
                                             "price": 1.6,
                                             "taxes": 21.0,
                                             "superMarketName": "Mercadona",
                                             "superMarketImage": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png"
                                         }
                                     ]
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has any product this list", content = @Content),
    })
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
    @Operation(summary = "getAll", description = "Find all product in the database for angular application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The product has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetProductDTO.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "content": [
                                            {
                                                "id": "ba9f2f92-5c4d-49ce-8f01-ee073447bfb8",
                                                "productName": "tomato",
                                                "productImage": "https://prod-mercadona.imgix.net/images/d9b74d242c05f180baf1f2af25255867.jpg",
                                                "productBrand": "hacendado",
                                                "price": 1.03,
                                                "taxes": 21.0,
                                                "superMarketName": "Mercadona",
                                                "superMarketImage": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png"
                                            },
                                            {
                                                "id": "995e94d3-c39e-4422-9ae6-ff11aaab8ab2",
                                                "productName": "tomato",
                                                "productImage": "https://a0.soysuper.com/4f8b1a659801347bd2c1b8bd469910f1.1500.0.0.0.wmark.9d3c4d01.jpg",
                                                "productBrand": "classic",
                                                "price": 1.01,
                                                "taxes": 21.0,
                                                "superMarketName": "Carrefour",
                                                "superMarketImage": "https://static.carrefour.es/crs/cdn_static/c4corp-front/images/logos/og/logo-carrefour-og_3.jpg"
                                            },
                                            {
                                                "id": "da98fe64-3c82-4758-bc3d-1dc183fb2a7b",
                                                "productName": "tomato",
                                                "productImage": "https://www.dia.es/product_images/10677/10677_ISO_0_ES.jpg",
                                                "productBrand": "vegecampo",
                                                "price": 1.4,
                                                "taxes": 21.0,
                                                "superMarketName": "MAS",
                                                "superMarketImage": "https://blog.supermercadosmas.com/wp-content/uploads/2018/01/blog-prueba23.jpg"
                                            },
                                            {
                                                "id": "0b238cc4-e9b7-4b3a-8da0-822cd3dfe40d",
                                                "productName": "tomato",
                                                "productImage": "https://www.lidl.es/media/product/0/0/1/4/7/8/3/tomate-frito-con-cebolla-zoom--2.jpg",
                                                "productBrand": "freshona",
                                                "price": 1.23,
                                                "taxes": 21.0,
                                                "superMarketName": "ALDI",
                                                "superMarketImage": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/ALDI_Nord_Logo_2015.png/731px-ALDI_Nord_Logo_2015.png"
                                            },
                                            {
                                                "id": "fc1e37eb-2db4-456d-9d9d-8645bd14d626",
                                                "productName": "tomato",
                                                "productImage": "https://www.auchan.pt/dw/image/v2/BFRC_PRD/on/demandware.static/-/Sites-auchan-pt-master-catalog/default/dw0678a7a4/images/hi-res/002474035.jpg",
                                                "productBrand": "auchan",
                                                "price": 0.9,
                                                "taxes": 21.0,
                                                "superMarketName": "Cash Fre",
                                                "superMarketImage": "https://www.cashfres/wp-content/uploads/2020/02/CASH-FRESH.jpg"
                                            },
                                            {
                                                "id": "448bde02-d045-4d47-a912-40123f2665ad",
                                                "productName": "tomato",
                                                "productImage": "https://www.clara.es/medio/2021/02/22/tomate-frito-ferrer_477a097f_1000x1500.jpg",
                                                "productBrand": "ferrer",
                                                "price": 1.6,
                                                "taxes": 21.0,
                                                "superMarketName": "Mercadona",
                                                "superMarketImage": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png"
                                            }
                                        ],
                                        "size": 10,
                                        "totalElements": 6,
                                        "pageNumber": 0,
                                        "first": true,
                                        "last": true
                                    }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has any product this list", content = @Content),
    })
    @GetMapping("/all")
    public MyPage<GetProductDTO> getAll (@PageableDefault(page = 0, size = 10)Pageable pageable){
        return MyPage.of(service.findAll(pageable).map(GetProductDTO::of));
    }
    @Operation(summary = "createProduct", description = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of a new product", content = {
                    @Content(mediaType = "application/json", examples = { @ExampleObject(value =
                            """
                                            {
                                                "id": "448bde02-d045-4d47-a912-40123f2665ad",
                                                "productName": "tomato",
                                                "productImage": "https://www.clara.es/medio/2021/02/22/tomate-frito-ferrer_477a097f_1000x1500.jpg",
                                                "productBrand": "ferrer",
                                                "price": 1.6,
                                                "taxes": 21.0,
                                                "superMarketName": "Mercadona",
                                                "superMarketImage": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png"
                                            }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "The creation of the product has not been done", content = @Content),
            @ApiResponse(responseCode = "404", description = "Don't has any supermarket with this id", content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetProductDTO> createProduct (@RequestBody AddProductDTO nuevo){
        GetProductDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @Operation(summary = "deleteProduct", description = "Delete an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The product has been deleted"),
            @ApiResponse(responseCode = "404", description = "Unable to find the product to delete.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "editProduct", description = "Update an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The product has been updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetProductDTO.class), examples = {
                            @ExampleObject(value = """
                                            {
                                                "id": "448bde02-d045-4d47-a912-40123f2665ad",
                                                "productName": "tomato",
                                                "productImage": "https://www.clara.es/medio/2021/02/22/tomate-frito-ferrer_477a097f_1000x1500.jpg",
                                                "productBrand": "ferrer",
                                                "price": 1.6,
                                                "taxes": 21.0,
                                                "superMarketName": "Mercadona",
                                                "superMarketImage": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png"
                                            }
                                """) }) }),
            @ApiResponse(responseCode = "404", description = "Unable to find the product to update.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input for the product.", content = @Content)
    })
    @PutMapping("/{id}")
    public GetProductDTO editProduct (@Valid @RequestBody AddProductDTO edit, @PathVariable UUID id){
        return service.edit(edit, id);
    }

}
