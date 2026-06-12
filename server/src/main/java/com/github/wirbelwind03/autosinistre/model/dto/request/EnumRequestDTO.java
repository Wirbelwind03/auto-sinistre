package com.github.wirbelwind03.autosinistre.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnumRequestDTO {
    private String value;
    private String label;
}
