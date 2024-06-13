package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.dto.usuario.LoginUser;
import com.triana.salesianos.dam.Maen.dto.usuarioMaen.AddUsuarioMaen;
import com.triana.salesianos.dam.Maen.dto.usuarioMaen.GetUserMaenDTO;
import com.triana.salesianos.dam.Maen.model.Usuario;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import com.triana.salesianos.dam.Maen.security.jwt.JwtProvider;
import com.triana.salesianos.dam.Maen.security.jwt.JwtUserResponse;
import com.triana.salesianos.dam.Maen.service.UsuarioMaenService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "This class is the user controller which will have the" +
        " methods to log in and to create a user with user role maen.")
public class UserController {

    private final UsuarioMaenService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created", description = "Register was succesful", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)), examples = {
                            @ExampleObject(value = """
                                                    {
                                                        "id": "d8af5a46-1799-4cb1-a5d0-a60a6cd8e0dc",
                                                        "username": "pepeillo1",
                                                        "email": "pepeillo1@gmail.com",
                                                        "name": "pepeillo uno",
                                                        "role": "ROLE_USER",
                                                        "createdAt": "13/06/2024 18:52:55",
                                                        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkOGFmNWE0Ni0xNzk5LTRjYjEtYTVkMC1hNjBhNmNkOGUwZGMiLCJpYXQiOjE3MTgyOTc1NzUsImV4cCI6MTcxODM4Mzk3NX0.VPzLyvX8Ov6wjTWMH0FBLhR52Twk_JsFBY5Heo5O46miXWXN30lsx5f8BScL7PnBOk0jsW5GfobtuwXjHq53vA"
                                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "400 Bad Request", description = "Register was not succesful", content = @Content),
    })
    @PostMapping("/auth/register")
    public ResponseEntity<JwtUserResponse> createUserWithUserMaen(@Valid @RequestBody AddUsuarioMaen addUsuarioMaen) {
        UsuarioMaen usuario = userService.createUserMaen(addUsuarioMaen);
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(addUsuarioMaen.username(), addUsuarioMaen.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(usuario, token));
    }
    @Operation(summary = "Authenticate and generate JWT for user login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login successful", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": "04d0595e-45d5-4f63-8b53-1d79e9d84a5d",
                                                "username": "pepeillo",
                                                "email": "pepeillo@user.com",
                                                "name": "pepeillo",
                                                "role": "ROLE_USER",
                                                "createdAt": "13/06/2024 15:09:03",
                                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNGQwNTk1ZS00NWQ1LTRmNjMtOGI1My0xZDc5ZTlkODRhNWQiLCJpYXQiOjE3MTgyOTc1MDUsImV4cCI6MTcxODM4MzkwNX0.jOXsHbq-BPipTqRj-5ym7145JG8xlQYKTPQvyCFs9a45xWSfDdQcEqLW8qFX2YnIaPINzryDBYwvqlRQkKFH1w"
                                            }
                                            """
                            ))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginUser loginUser) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.username(),
                        loginUser.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        Usuario user = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token));

    }
    @GetMapping("/perfile/")
    public GetUserMaenDTO detailsUserMaen (@AuthenticationPrincipal UsuarioMaen u){
        return GetUserMaenDTO.of(u);
    }
}
