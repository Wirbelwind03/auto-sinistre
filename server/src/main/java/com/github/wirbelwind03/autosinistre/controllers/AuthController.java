package com.github.wirbelwind03.autosinistre.controllers;

import com.github.wirbelwind03.autosinistre.dto.RegisterRequestDTO;
import com.github.wirbelwind03.autosinistre.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public String register(@RequestBody RegisterRequestDTO request){
        authService.register(request);
        return  "Success";
    }

    @PostMapping("/login")
    public String login(){
        return  "Success";
    }
}
