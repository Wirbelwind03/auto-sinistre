package com.github.wirbelwind03.autosinistre.model.dto.request;

import com.github.wirbelwind03.autosinistre.model.entity.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String email;
    private String password;
    private Role role;
}
