package com.github.wirbelwind03.autosinistre.controller;

import com.github.wirbelwind03.autosinistre.model.dto.request.FuelTypeRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.request.SinistreTypeRequestDTO;
import com.github.wirbelwind03.autosinistre.model.enums.sinistre.SinistreTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/sinistre")
@RequiredArgsConstructor
public class SinistreController {

    @GetMapping("/types")
    public ResponseEntity<List<SinistreTypeRequestDTO>> getSinistreTypes(){
        List<SinistreTypeRequestDTO> sinistreTypes = Arrays.stream(SinistreTypeEnum.values())
                .map(e -> new SinistreTypeRequestDTO(
                        e.name(),
                        e.getLabel(),
                        e.getDescription(),
                        e.getIconName(),
                        e.getColor())
                )
                .toList();
        return ResponseEntity.ok(sinistreTypes);
    }
}
