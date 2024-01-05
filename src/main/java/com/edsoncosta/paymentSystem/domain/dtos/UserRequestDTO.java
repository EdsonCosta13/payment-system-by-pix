package com.edsoncosta.paymentSystem.domain.dtos;

import com.edsoncosta.paymentSystem.domain.models.User;

public record UserRequestDTO(String name, String email, String password) {

    public User toModel()
    {
       return new User(name,email,password);
    }

}