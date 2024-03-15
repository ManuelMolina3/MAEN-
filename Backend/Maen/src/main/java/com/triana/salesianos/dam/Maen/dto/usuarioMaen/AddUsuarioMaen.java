package com.triana.salesianos.dam.Maen.dto.usuarioMaen;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddUsuarioMaen(
        @NotBlank(message = "{addUsuarioMaen.username.notblank}")
        String username,

        @NotBlank(message = "{addUsuarioMaen.password.notblank}")
        @Size(min = 6, message = "{addUsuarioMaen.password.size}")
        String password,

        @NotBlank(message = "{addUsuarioMaen.verifyPassword.notblank}")
        String verifyPassword,

        @NotBlank(message = "{addUsuarioMaen.email.notblank}")
        @Email(message = "{addUsuarioMaen.email.email}")
        String email,

        @NotBlank(message = "{addUsuarioMaen.name.notblank}")
        String name,

        int numMemberOfFamily,

        double salary

) {
}
