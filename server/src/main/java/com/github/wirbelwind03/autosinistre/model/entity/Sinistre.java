package com.github.wirbelwind03.autosinistre.model.entity;

import com.github.wirbelwind03.autosinistre.model.enums.StatusSinistreEnum;
import com.github.wirbelwind03.autosinistre.model.enums.TypeSinistreEnum;
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
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSinistreEnum type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSinistreEnum status;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime declarationDate;
}
