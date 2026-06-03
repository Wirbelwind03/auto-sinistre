package com.github.wirbelwind03.autosinistre.controller;

import com.github.wirbelwind03.autosinistre.model.dto.request.AuthRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.request.RegisterRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.response.AuthResponseDTO;
import com.github.wirbelwind03.autosinistre.service.AuthService;
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
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request){
        return  ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }
}
