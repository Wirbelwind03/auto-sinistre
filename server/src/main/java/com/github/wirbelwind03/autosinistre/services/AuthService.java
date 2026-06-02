package com.github.wirbelwind03.autosinistre.services;

import com.github.wirbelwind03.autosinistre.dto.request.AuthRequestDTO;
import com.github.wirbelwind03.autosinistre.dto.response.AuthResponseDTO;
import com.github.wirbelwind03.autosinistre.dto.request.RegisterRequestDTO;
import com.github.wirbelwind03.autosinistre.models.User;
import com.github.wirbelwind03.autosinistre.repositories.UserRepository;
import com.github.wirbelwind03.autosinistre.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Un compte existe déjà avec cette email.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return AuthResponseDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public AuthResponseDTO login(AuthRequestDTO requestDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword())
        );

        User user = userRepository.findByEmail(requestDTO.getEmail());
        if (user == null){
            throw new IllegalArgumentException("Utilisateur introuvable");
        }

        String token = jwtUtil.generateToken(user);

        return AuthResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
