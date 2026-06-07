package com.github.wirbelwind03.autosinistre.service;

import com.github.wirbelwind03.autosinistre.exception.InvalidEmailException;
import com.github.wirbelwind03.autosinistre.exception.UserAlreadyExistException;
import com.github.wirbelwind03.autosinistre.model.dto.request.AuthRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.response.AuthResponseDTO;
import com.github.wirbelwind03.autosinistre.model.dto.request.RegisterRequestDTO;
import com.github.wirbelwind03.autosinistre.model.entity.Role;
import com.github.wirbelwind03.autosinistre.model.entity.User;
import com.github.wirbelwind03.autosinistre.model.enums.RoleEnum;
import com.github.wirbelwind03.autosinistre.repository.RoleRepository;
import com.github.wirbelwind03.autosinistre.repository.UserRepository;
import com.github.wirbelwind03.autosinistre.security.JwtUtil;
import com.github.wirbelwind03.autosinistre.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistException();
        }

        if (!ValidationUtils.isValidEmail(request.getEmail())){
            throw new InvalidEmailException();
        }

        // Mettre le role client au nouveau utilisateur
        Optional<Role> role = roleRepository.findByName(RoleEnum.CLIENT);
        if (role.isEmpty()) {
            return null;
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role.get())
                .name(request.getName())
                .firstName(request.getFirstName())
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

        Optional<User> user = userRepository.findByEmail(requestDTO.getEmail());
        if (user.isEmpty()){
            throw new IllegalArgumentException("Utilisateur introuvable");
        }

        String token = jwtUtil.generateToken(user.get());

        return AuthResponseDTO.builder()
                .token(token)
                .email(user.get().getEmail())
                .role(user.get().getRole())
                .build();
    }
}
