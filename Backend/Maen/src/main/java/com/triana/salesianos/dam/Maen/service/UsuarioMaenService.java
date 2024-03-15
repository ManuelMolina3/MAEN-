package com.triana.salesianos.dam.Maen.service;

import com.triana.salesianos.dam.Maen.dto.usuarioMaen.AddUsuarioMaen;
import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import com.triana.salesianos.dam.Maen.repository.UsuarioMaenRepository;
import com.triana.salesianos.dam.Maen.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioMaenService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioMaenRepository usuarioMaenRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioMaen createUserMaen(AddUsuarioMaen addUsuarioMaen){
        UsuarioMaen user= new UsuarioMaen();
        user.setUsername(addUsuarioMaen.username());
        user.setPassword(passwordEncoder.encode(addUsuarioMaen.password()));
        user.setEmail(addUsuarioMaen.email());
        user.setName(addUsuarioMaen.name());
        user.setNumMembersOfFamily(addUsuarioMaen.numMemberOfFamily());
        user.setSalary(addUsuarioMaen.salary());

        return usuarioMaenRepository.save(user);
    }
}
