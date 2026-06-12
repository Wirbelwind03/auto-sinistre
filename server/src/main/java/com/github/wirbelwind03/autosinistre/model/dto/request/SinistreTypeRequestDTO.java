package com.github.wirbelwind03.autosinistre.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SinistreTypeRequestDTO {
    private String value;
    private String label;
    private String description;
    private String icon;
    private String color;
}
