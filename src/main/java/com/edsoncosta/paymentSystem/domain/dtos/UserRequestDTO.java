package com.edsoncosta.paymentSystem.domain.dtos;

import com.edsoncosta.paymentSystem.domain.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotNull(message = "O nome não pode ser nulo!")
        @NotBlank(message = "O nome não pode ser vazio")
        String name,

        @NotNull(message = "O email não pode ser nulo!")
        @NotBlank(message = "O email não pode ser vazio")
        @Email
        String email,

        @NotNull(message = "A password não pode ser nulo!")
        @NotBlank(message = "A password não pode ser vazio")
        @Size(min = 8,message = "A senha deve ter no inimo 8 caracteres!")
        String password
) {

    public User toModel()
    {
       return new User(name,email,password);
    }

}
