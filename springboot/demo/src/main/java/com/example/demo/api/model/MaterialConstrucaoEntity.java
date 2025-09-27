package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "TB_MATERIAL_CONSTRUCAO")
public class MaterialConstrucaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_MATERIAL")
    private Integer codMaterial;

    @Column(name = "CODIGO_PRODUTO", length = 50, nullable = false)
    private String codigoProduto;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "COR", length = 50)
    private String cor;

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @Column(name = "MATERIA_PRIMA", length = 100)
    private String materiaPrima;

    // relação com filial
    @JsonBackReference("filial-materiais")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CODIGO_FILIAL", nullable = false,
            foreignKey = @ForeignKey(name = "FK_MATERIAL_FILIAL"))
    private FilialEntity filial;
}
