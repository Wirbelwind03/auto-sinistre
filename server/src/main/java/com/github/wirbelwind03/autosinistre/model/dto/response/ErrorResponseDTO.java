package com.github.wirbelwind03.autosinistre.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {
    private String errorCode;
    private Object message;
}
