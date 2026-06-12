package com.github.wirbelwind03.autosinistre.model.mapper;

import com.github.wirbelwind03.autosinistre.model.dto.response.GetUserResponseDTO;
import com.github.wirbelwind03.autosinistre.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public GetUserResponseDTO toDTO(User user){
        return GetUserResponseDTO.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .name(user.getName())
                .role(user.getRole().getName().name())
                .build();
    }
}
