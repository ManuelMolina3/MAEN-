package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.electricityContract.AddElectricityContractDTO;
import com.triana.salesianos.dam.Maen.dto.electricityContract.GetElectricityContractDTO;
import com.triana.salesianos.dam.Maen.service.ElectricityContractService;
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
@RequestMapping("/contract")
@RequiredArgsConstructor
@Tag(name = "Electricity contract", description = "This is the controller when operates all methods of electricity contract entity")
public class ElectricityContractController {

    private final ElectricityContractService service;

    @Operation(summary = "getAll", description = "Find All electricity contract in the database for flutter application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The electricity contract has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetElectricityContractDTO.class)), examples = {
                            @ExampleObject(value = """
                                        {
                                            "content": [
                                                {
                                                    "id": "4dd03371-7871-4f12-9555-e797c3d6e8fc",
                                                    "priceEnergy": 0.26,
                                                    "discountEnergy": 5.0,
                                                    "pricePower": 0.13,
                                                    "priceEquipment": 1.76,
                                                    "taxes": 21.0,
                                                    "companyName": "TotalEnergy",
                                                    "companyLogotype": "https://brandemia.org/contenido/subidas/2021/05/portada-total-imagenes-brandemia-web-1000x670.jpg",
                                                    "companyId": "d92057c4-ac58-4738-99e1-2e05e1596308"
                                                },
                                                {
                                                    "id": "1efb96ec-c006-4ce7-abf9-c0bd4d761a47",
                                                    "priceEnergy": 0.27,
                                                    "discountEnergy": 4.0,
                                                    "pricePower": 0.14,
                                                    "priceEquipment": 1.56,
                                                    "taxes": 21.0,
                                                    "companyName": "Iberdrola",
                                                    "companyLogotype": "https://elperiodicodelaenergia.com/wp-content/uploads/2023/05/fotonoticia_20230524105438_1920.jpg",
                                                    "companyId": "94f4f1b0-fb02-4f2a-bf00-e0e751843028"
                                                },
                                                {
                                                    "id": "586ab7bc-5a26-4123-9328-07d082e4e9c8",
                                                    "priceEnergy": 0.21,
                                                    "discountEnergy": 6.0,
                                                    "pricePower": 0.1,
                                                    "priceEquipment": 1.79,
                                                    "taxes": 21.0,
                                                    "companyName": "Endesa",
                                                    "companyLogotype": "https://www.endesa.com/content/dam/endesa-com/home/prensa/imagenes/kit-de-prensa/endesa-logo-prensa.jpg",
                                                    "companyId": "4b08d856-f464-4bef-ad8d-a92186f1b237"
                                                },
                                                {
                                                    "id": "8bd68ede-ea54-47e1-9e95-935ea933af60",
                                                    "priceEnergy": 0.3,
                                                    "discountEnergy": 10.0,
                                                    "pricePower": 0.18,
                                                    "priceEquipment": 1.56,
                                                    "taxes": 21.0,
                                                    "companyName": "Naturgy",
                                                    "companyLogotype": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                                    "companyId": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7"
                                                }
                                            ],
                                            "size": 12,
                                            "totalElements": 4,
                                            "pageNumber": 0,
                                            "first": true,
                                            "last": true
                                        }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has any electricity contract this list", content = @Content),
    })
    @GetMapping("/")
    public MyPage<GetElectricityContractDTO> getAll(@PageableDefault(size = 12, page = 0) Pageable pageable){
        return service.getAll(pageable);
    }
    @GetMapping("/{id}")
    public GetElectricityContractDTO getContractById (@PathVariable UUID id){
        return service.getContractById(id);
    }

    @GetMapping("/{companyId}")
    public List<GetElectricityContractDTO> getContractByCompany (@PathVariable UUID companyId){
        return service.getContractByCompany(companyId);
    }
    @Operation(summary = "getAllA", description = "Find All electricity contract in the database for angular application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The electricity contract has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetElectricityContractDTO.class)), examples = {
                            @ExampleObject(value = """
                                        {
                                            "content": [
                                                {
                                                    "id": "4dd03371-7871-4f12-9555-e797c3d6e8fc",
                                                    "priceEnergy": 0.26,
                                                    "discountEnergy": 5.0,
                                                    "pricePower": 0.13,
                                                    "priceEquipment": 1.76,
                                                    "taxes": 21.0,
                                                    "companyName": "TotalEnergy",
                                                    "companyLogotype": "https://brandemia.org/contenido/subidas/2021/05/portada-total-imagenes-brandemia-web-1000x670.jpg",
                                                    "companyId": "d92057c4-ac58-4738-99e1-2e05e1596308"
                                                },
                                                {
                                                    "id": "1efb96ec-c006-4ce7-abf9-c0bd4d761a47",
                                                    "priceEnergy": 0.27,
                                                    "discountEnergy": 4.0,
                                                    "pricePower": 0.14,
                                                    "priceEquipment": 1.56,
                                                    "taxes": 21.0,
                                                    "companyName": "Iberdrola",
                                                    "companyLogotype": "https://elperiodicodelaenergia.com/wp-content/uploads/2023/05/fotonoticia_20230524105438_1920.jpg",
                                                    "companyId": "94f4f1b0-fb02-4f2a-bf00-e0e751843028"
                                                },
                                                {
                                                    "id": "586ab7bc-5a26-4123-9328-07d082e4e9c8",
                                                    "priceEnergy": 0.21,
                                                    "discountEnergy": 6.0,
                                                    "pricePower": 0.1,
                                                    "priceEquipment": 1.79,
                                                    "taxes": 21.0,
                                                    "companyName": "Endesa",
                                                    "companyLogotype": "https://www.endesa.com/content/dam/endesa-com/home/prensa/imagenes/kit-de-prensa/endesa-logo-prensa.jpg",
                                                    "companyId": "4b08d856-f464-4bef-ad8d-a92186f1b237"
                                                },
                                                {
                                                    "id": "8bd68ede-ea54-47e1-9e95-935ea933af60",
                                                    "priceEnergy": 0.3,
                                                    "discountEnergy": 10.0,
                                                    "pricePower": 0.18,
                                                    "priceEquipment": 1.56,
                                                    "taxes": 21.0,
                                                    "companyName": "Naturgy",
                                                    "companyLogotype": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                                    "companyId": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7"
                                                }
                                            ],
                                            "size": 10,
                                            "totalElements": 4,
                                            "pageNumber": 0,
                                            "first": true,
                                            "last": true
                                        }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has any electricity contract this list", content = @Content),
    })
    @GetMapping("/all")
    public MyPage<GetElectricityContractDTO> getAllA (@PageableDefault(page = 0, size = 10)Pageable pageable){
       return MyPage.of(service.findAll(pageable).map(GetElectricityContractDTO::of));
    }    @Operation(summary = "createElectricityContract", description = "Create a new Contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of a new contract", content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value =
                                                """
                                                {
                                                    "id": "8bd68ede-ea54-47e1-9e95-935ea933af60",
                                                    "priceEnergy": 0.3,
                                                    "discountEnergy": 10.0,
                                                    "pricePower": 0.18,
                                                    "priceEquipment": 1.56,
                                                    "taxes": 21.0,
                                                    "companyName": "Naturgy",
                                                    "companyLogotype": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                                    "companyId": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7"
                                                }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "The creation of the contract has not been done", content = @Content),
            @ApiResponse(responseCode = "404", description = "Don't has any electricity company with this id", content = @Content)

    })
    @PostMapping("/")
    public ResponseEntity<GetElectricityContractDTO> createElectricityContract (@RequestBody AddElectricityContractDTO nuevo){
        GetElectricityContractDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @Operation(summary = "deleteElectricityContract", description = "Delete an existing contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The contract has been deleted"),
            @ApiResponse(responseCode = "404", description = "Unable to find the contract to delete.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteElectricityContract(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "editElectricityContract", description = "Update an existing contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The contract has been updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetElectricityContractDTO.class), examples = {
                            @ExampleObject(value = """
                                                {
                                                    "id": "8bd68ede-ea54-47e1-9e95-935ea933af60",
                                                    "priceEnergy": 0.3,
                                                    "discountEnergy": 10.0,
                                                    "pricePower": 0.18,
                                                    "priceEquipment": 1.56,
                                                    "taxes": 21.0,
                                                    "companyName": "Naturgy",
                                                    "companyLogotype": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                                    "companyId": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7"
                                                }
                                """) }) }),
            @ApiResponse(responseCode = "404", description = "Unable to find the contract to update.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input for the contract.", content = @Content)
    })
    @PutMapping("/{id}")
    public GetElectricityContractDTO editElectricityContract (@Valid @RequestBody AddElectricityContractDTO edited, @PathVariable UUID id){
        return service.edit(edited, id);
    }

}
