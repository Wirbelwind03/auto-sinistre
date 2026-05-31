package com.github.wirbelwind03.autosinistre.dto;

import com.github.wirbelwind03.autosinistre.models.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AuthResponseDTO {
    private String token;
    private String email;
    private Role role;
}
