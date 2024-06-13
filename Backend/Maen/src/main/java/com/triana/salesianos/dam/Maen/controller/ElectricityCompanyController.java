package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.MyPage;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.AddElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.electricityCompany.GetElectricityCompanyDTO;
import com.triana.salesianos.dam.Maen.dto.supermarket.GetSupermarketDTO;
import com.triana.salesianos.dam.Maen.service.ElectricityCompanyService;
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
@Tag(name = "Electricity Company", description = "This is the controller when operates all methods of electricity companies entity")
@RequestMapping("/company")
public class ElectricityCompanyController {

    private final ElectricityCompanyService service;
    @Operation(summary = "getAll", description = "Find All electricity company in the database for company list in Angular")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The companies has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetElectricityCompanyDTO.class)), examples = {
                            @ExampleObject(value = """
                                {
                                    "content": [
                                        {
                                            "id": "d92057c4-ac58-4738-99e1-2e05e1596308",
                                            "name": "TotalEnergy",
                                            "urlImage": "https://brandemia.org/contenido/subidas/2021/05/portada-total-imagenes-brandemia-web-1000x670.jpg",
                                            "numOfContract": 1
                                        },
                                        {
                                            "id": "94f4f1b0-fb02-4f2a-bf00-e0e751843028",
                                            "name": "Iberdrola",
                                            "urlImage": "https://elperiodicodelaenergia.com/wp-content/uploads/2023/05/fotonoticia_20230524105438_1920.jpg",
                                            "numOfContract": 1
                                        },
                                        {
                                            "id": "4b08d856-f464-4bef-ad8d-a92186f1b237",
                                            "name": "Endesa",
                                            "urlImage": "https://www.endesa.com/content/dam/endesa-com/home/prensa/imagenes/kit-de-prensa/endesa-logo-prensa.jpg",
                                            "numOfContract": 1
                                        },
                                        {
                                            "id": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7",
                                            "name": "Naturgy",
                                            "urlImage": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                            "numOfContract": 1
                                        }
                                    ],
                                    "size": 10,
                                    "totalElements": 4,
                                    "pageNumber": 0,
                                    "first": true,
                                    "last": true
                                }
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has company this list", content = @Content),
    })
    @GetMapping("/")
    public MyPage<GetElectricityCompanyDTO> getAll (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return MyPage.of(service.findAll(pageable));
    }
    @Operation(summary = "findAll", description = "Find All companies in the database for create new contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The companies has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetElectricityCompanyDTO.class)), examples = {
                            @ExampleObject(value = """
                                [
                                    {
                                        "id": "d92057c4-ac58-4738-99e1-2e05e1596308",
                                        "name": "TotalEnergy",
                                        "urlImage": "https://brandemia.org/contenido/subidas/2021/05/portada-total-imagenes-brandemia-web-1000x670.jpg",
                                        "numOfContract": 0
                                    },
                                    {
                                        "id": "94f4f1b0-fb02-4f2a-bf00-e0e751843028",
                                        "name": "Iberdrola",
                                        "urlImage": "https://elperiodicodelaenergia.com/wp-content/uploads/2023/05/fotonoticia_20230524105438_1920.jpg",
                                        "numOfContract": 0
                                    },
                                    {
                                        "id": "4b08d856-f464-4bef-ad8d-a92186f1b237",
                                        "name": "Endesa",
                                        "urlImage": "https://www.endesa.com/content/dam/endesa-com/home/prensa/imagenes/kit-de-prensa/endesa-logo-prensa.jpg",
                                        "numOfContract": 0
                                    },
                                    {
                                        "id": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7",
                                        "name": "Naturgy",
                                        "urlImage": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                        "numOfContract": 0
                                    }
                                ]
                                                         """) }) }),
            @ApiResponse(responseCode = "404", description = "Don't has company this list", content = @Content),
    })
    @GetMapping("/all")
    public List<GetElectricityCompanyDTO> findAll(){
        return service.findAll();
    }
    @Operation(summary = "createProduct", description = "Create a new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation of a new product", content = {
                    @Content(mediaType = "application/json", examples = { @ExampleObject(value =
                            """
                                    {
                                        "id": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7",
                                        "name": "Naturgy",
                                        "urlImage": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                        "numOfContract": 0
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "The creation of the product has not been done", content = @Content)

    })
    @PostMapping("/")
    public ResponseEntity<GetElectricityCompanyDTO> createProduct (@RequestBody AddElectricityCompanyDTO nuevo){
        GetElectricityCompanyDTO create = service.save(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(create.id()).toUri();
        return ResponseEntity.created(createdURI).body(create);
    }
    @Operation(summary = "deleteElectricityCompany", description = "Delete an existing company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The company has been deleted"),
            @ApiResponse(responseCode = "404", description = "Unable to find the company to delete.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteElectricityCompany(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "editElectricityCompany", description = "Update an existing company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The company has been updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetElectricityCompanyDTO.class), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": "8a28b5f0-c23f-451b-97f1-ad6bb190eff7",
                                        "name": "Naturgy",
                                        "urlImage": "https://ieeb.fundacion-biodiversidad.es/sites/default/files/naturgy_rgb_principal_positiva_0.jpg",
                                        "numOfContract": 0
                                    }
                                """) }) }),
            @ApiResponse(responseCode = "404", description = "Unable to find the company to update.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input for the company.", content = @Content)
    })
    @PutMapping("/{id}")
    public GetElectricityCompanyDTO editElectricityCompany (@Valid @RequestBody AddElectricityCompanyDTO edit, @PathVariable UUID id){
        return service.edit(edit, id);
    }

}
