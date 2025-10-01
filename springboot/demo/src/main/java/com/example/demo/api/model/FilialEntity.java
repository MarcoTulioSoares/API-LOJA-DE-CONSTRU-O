package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "tb_filial")
public class FilialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_filial", nullable = false, updatable = false,
            columnDefinition = "INT AUTO_INCREMENT")
    private Integer idFilial;

    @Column(name = "nome_filial", nullable = false, length = 150)
    private String nome;

    @JsonManagedReference("filial-ferramentas")
    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<FerramentaEntity> ferramentas;

    @JsonManagedReference("filial-materiais")
    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<MaterialConstrucaoEntity> materiais;
}
