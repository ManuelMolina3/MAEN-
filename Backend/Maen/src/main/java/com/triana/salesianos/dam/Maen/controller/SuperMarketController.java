package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.supermarket.AddSupermarketDTO;
import com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO;
import com.triana.salesianos.dam.Maen.service.SuperMarketService;
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
@RequiredArgsConstructor
@Tag(name = "SuperMarket", description = "This is the controller when operates all methods of supermarket entity")
@RequestMapping("/supermarket")
public class SuperMarketController {

    private final SuperMarketService service;

    @Operation(summary = "getAll", description = "Find All Supermarket in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The supermarket has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetSupermarketDTO.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "content": [
                                            {
                                                "id": "5a810d7a-a62d-42a4-830c-905c78f1ce53",
                                                "name": "Mercadona",
                                                "logotype": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png",
                                                "numOfProduct": 2
                                            },
                                            {
                                                "id": "560cb288-b039-464d-89f5-631a6794e5f1",
                                                "name": "Carrefour",
                                                "logotype": "https://static.carrefour.es/crs/cdn_static/c4corp-front/images/logos/og/logo-carrefour-og_3.jpg",
                                                "numOfProduct": 1
                                            },
                                            {
                                                "id": "938bbee7-c02d-4eff-b7fb-65de157e2e0c",
                                                "name": "MAS",
                                                "logotype": "https://blog.supermercadosmas.com/wp-content/uploads/2018/01/blog-prueba23.jpg",
                                                "numOfProduct": 1
                                            },
                                            {
                                                "id": "698677de-ce3f-4a5e-9c90-c3b1ce2d1e97",
                                                "name": "ALDI",
                                                "logotype": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/ALDI_Nord_Logo_2015.png/731px-ALDI_Nord_Logo_2015.png",
                                                "numOfProduct": 1
                                            },
                                            {
                                                "id": "64c55bc0-9b75-46ae-b299-2cb7f8d1554f",
                                                "name": "Cash Fre",
                                                "logotype": "https://www.cashfres/wp-content/uploads/2020/02/CASH-FRESH.jpg",
                                                "numOfProduct": 1
                                            }
                                        ],
                                        "size": 10,
                                        "totalElements": 5,
                                        "pageNumber": 0,
                                        "first": true,
                                        "last": true
                                    }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has supermarket this list", content = @Content),
    })
    @GetMapping("/")
    public MyPage<GetSupermarketDTO> getAll (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return MyPage.of((service.findAll(pageable)));
    }
    @Operation(summary = "findAll", description = "Find All Supermarket in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The supermarket has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetSupermarketDTO.class)), examples = {
                            @ExampleObject(value = """
                                [
                                    {
                                        "id": "5a810d7a-a62d-42a4-830c-905c78f1ce53",
                                        "name": "Mercadona",
                                        "logotype": "https://barcelonamaculafound.org/wp-content/uploads/2019/12/mercadona.png",
                                        "numOfProduct": 0
                                    },
                                    {
                                        "id": "560cb288-b039-464d-89f5-631a6794e5f1",
                                        "name": "Carrefour",
                                        "logotype": "https://static.carrefour.es/crs/cdn_static/c4corp-front/images/logos/og/logo-carrefour-og_3.jpg",
                                        "numOfProduct": 0
                                    },
                                    {
                                        "id": "938bbee7-c02d-4eff-b7fb-65de157e2e0c",
                                        "name": "MAS",
                                        "logotype": "https://blog.supermercadosmas.com/wp-content/uploads/2018/01/blog-prueba23.jpg",
                                        "numOfProduct": 0
                                    },
                                    {
                                        "id": "698677de-ce3f-4a5e-9c90-c3b1ce2d1e97",
                                        "name": "ALDI",
                                        "logotype": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/ALDI_Nord_Logo_2015.png/731px-ALDI_Nord_Logo_2015.png",
                                        "numOfProduct": 0
                                    },
                                    {
                                        "id": "64c55bc0-9b75-46ae-b299-2cb7f8d1554f",
                                        "name": "Cash Fre",
                                        "logotype": "https://www.cashfres/wp-content/uploads/2020/02/CASH-FRESH.jpg",
                                        "numOfProduct": 0
                                    }
                                ]
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has supermarket this list", content = @Content),
    })
    @GetMapping("/all")
    public List<GetSupermarketDTO> findAll (){
        return service.getAll().stream().map(GetSupermarketDTO::of).toList();
    }

    @Operation(summary = "createdSupermarket", description = "Create a new Supermarket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of a new supermarket", content = {
                    @Content(mediaType = "application/json", examples = { @ExampleObject(value =
                            """
                                    {
                                        "id": "64c55bc0-9b75-46ae-b299-2cb7f8d1554f",
                                        "name": "Cash Fre",
                                        "logotype": "https://www.cashfres/wp-content/uploads/2020/02/CASH-FRESH.jpg",
                                        "numOfProduct": 0
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "The creation of the supermarket has not been done", content = @Content)

    })
    @PostMapping("/")
    public ResponseEntity<GetSupermarketDTO> createdSupermarket (@RequestBody AddSupermarketDTO nuevo){
        GetSupermarketDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @Operation(summary = "deleteSupermarket", description = "Delete an existing supermarket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The supermarket has been deleted"),
            @ApiResponse(responseCode = "404", description = "Unable to find the supermarket to delete.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupermarket(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "editSupermarket", description = "Update an existing Supermarket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The supermarket has been updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetSupermarketDTO.class), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": "64c55bc0-9b75-46ae-b299-2cb7f8d1554f",
                                        "name": "Cash Fre",
                                        "logotype": "https://www.cashfres/wp-content/uploads/2020/02/CASH-FRESH.jpg",
                                        "numOfProduct": 0
                                    }
                                """) }) }),
            @ApiResponse(responseCode = "404", description = "Unable to find the supermarket to update.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input for the supermarket.", content = @Content)
    })
    @PutMapping("/{id}")
    public GetSupermarketDTO editSupermarket (@Valid @RequestBody AddSupermarketDTO edit, @PathVariable UUID id){
        return service.edit(edit, id);
    }
}
