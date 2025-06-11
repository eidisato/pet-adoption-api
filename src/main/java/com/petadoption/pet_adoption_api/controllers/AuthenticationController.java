package com.petadoption.pet_adoption_api.controllers;

import com.petadoption.pet_adoption_api.dtos.AuthenticationRecordDto;
import com.petadoption.pet_adoption_api.dtos.LoginResponseRecordDto;
import com.petadoption.pet_adoption_api.dtos.RegisterRecordDto;
import com.petadoption.pet_adoption_api.model.User;
import com.petadoption.pet_adoption_api.model.UserRole;
import com.petadoption.pet_adoption_api.repositories.UserRepository;
import com.petadoption.pet_adoption_api.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseRecordDto> login(@RequestBody @Valid AuthenticationRecordDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseRecordDto(token));
    }
}