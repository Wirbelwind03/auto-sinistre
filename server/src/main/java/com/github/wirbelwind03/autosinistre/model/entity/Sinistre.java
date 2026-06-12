package com.github.wirbelwind03.autosinistre.model.entity;

import com.github.wirbelwind03.autosinistre.model.enums.sinistre.SinistreSeverityEnum;
import com.github.wirbelwind03.autosinistre.model.enums.sinistre.SinistreStatusEnum;
import com.github.wirbelwind03.autosinistre.model.enums.sinistre.SinistreTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sinistres")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sinistre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SinistreStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SinistreTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SinistreSeverityEnum severity;

    @Column(nullable = false)
    private LocalDateTime declarationDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;
}
