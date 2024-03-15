package com.triana.salesianos.dam.Maen.dto.usuarioMaen;

import com.triana.salesianos.dam.Maen.model.UsuarioMaen;
import lombok.Builder;

@Builder
public record GetUserMaenDTO(
        String username,
        String email,

        String name,
        int numMemberOfFamily,
        double salary


) {
    public static GetUserMaenDTO of (UsuarioMaen usuario){
        return GetUserMaenDTO.builder()
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .name(usuario.getName())
                .numMemberOfFamily(usuario.getNumMembersOfFamily())
                .salary(usuario.getSalary())
                .build();
    }

}
