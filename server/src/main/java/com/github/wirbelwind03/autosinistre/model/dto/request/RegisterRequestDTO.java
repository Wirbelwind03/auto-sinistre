package com.github.wirbelwind03.autosinistre.model.dto.request;

import com.github.wirbelwind03.autosinistre.model.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;
}
