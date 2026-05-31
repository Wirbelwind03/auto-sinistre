package com.github.wirbelwind03.autosinistre.services;

import com.github.wirbelwind03.autosinistre.configurations.SecurityConfig;
import com.github.wirbelwind03.autosinistre.dto.AuthResponseDTO;
import com.github.wirbelwind03.autosinistre.dto.RegisterRequestDTO;
import com.github.wirbelwind03.autosinistre.models.Role;
import com.github.wirbelwind03.autosinistre.models.User;
import com.github.wirbelwind03.autosinistre.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SecurityConfig securityConfig;
    private final UserRepository userRepository;

    public AuthResponseDTO register(RegisterRequestDTO request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Un compte existe déjà avec cette email.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(securityConfig.passwordEncoder().encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return AuthResponseDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
