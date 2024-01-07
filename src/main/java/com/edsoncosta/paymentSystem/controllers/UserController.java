package com.edsoncosta.paymentSystem.controllers;


import com.edsoncosta.paymentSystem.domain.dtos.UserRequestDTO;
import com.edsoncosta.paymentSystem.domain.dtos.UserResponseDTO;
import com.edsoncosta.paymentSystem.domain.models.User;
import com.edsoncosta.paymentSystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/store")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO data) throws Exception {

        User user=data.toModel();
        UserResponseDTO userSaved=this.userService.registerUser(user);

        return ResponseEntity.ok().body(userSaved);
    }
}
