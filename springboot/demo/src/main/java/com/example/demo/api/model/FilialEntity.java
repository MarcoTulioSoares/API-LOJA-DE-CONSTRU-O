package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "TB_FILIAL")
public class FilialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_FILIAL", nullable = false, updatable = false,
            columnDefinition = "INT AUTO_INCREMENT")
    private Integer idLancamento;

    @Column(name = "NOME_FILIAL", nullable = false, length = 150)
    private String nome;

    @JsonManagedReference("filial-ferramentas")
    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<FerramentaEntity> ferramentas;

    @JsonManagedReference("filial-materiais")
    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<MaterialConstrucaoEntity> materiais;
}
