package com.triana.salesianos.dam.Maen.controller;

import com.triana.salesianos.dam.Maen.dto.usuario.LoginUser;
import com.triana.salesianos.dam.Maen.dto.usuarioMaen.AddUsuarioMaen;
import com.triana.salesianos.dam.Maen.dto.usuarioMaen.GetUserMaenDTO;
import com.triana.salesianos.dam.Maen.model.Usuario;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import com.triana.salesianos.dam.Maen.security.jwt.JwtProvider;
import com.triana.salesianos.dam.Maen.security.jwt.JwtUserResponse;
import com.triana.salesianos.dam.Maen.service.UsuarioMaenService;
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
    @PostMapping("/auth/register")
    public ResponseEntity<JwtUserResponse> createUserWithUserMaen(@Valid @RequestBody AddUsuarioMaen addUsuarioMaen) {
        UsuarioMaen usuario = userService.createUserMaen(addUsuarioMaen);
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(addUsuarioMaen.username(), addUsuarioMaen.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(usuario, token));
    }

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
