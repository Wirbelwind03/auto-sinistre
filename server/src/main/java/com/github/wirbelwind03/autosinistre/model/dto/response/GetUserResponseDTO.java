package com.github.wirbelwind03.autosinistre.model.dto.response;

import com.github.wirbelwind03.autosinistre.model.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponseDTO {
    private String email;
    private String firstName;
    private String name;
    private String role;
}
