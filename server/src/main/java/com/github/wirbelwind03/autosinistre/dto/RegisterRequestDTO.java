package com.github.wirbelwind03.autosinistre.dto;

import com.github.wirbelwind03.autosinistre.models.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String email;
    private String password;
    private Role role;
}
