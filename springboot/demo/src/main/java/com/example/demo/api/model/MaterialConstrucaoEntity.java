package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_material_construcao")
public class MaterialConstrucaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_material", nullable = false, updatable = false,
            columnDefinition = "INT AUTO_INCREMENT")
    private Integer codMaterial;

    @Column(name = "codigo_produto", length = 50, nullable = false)
    private String codigoProduto;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "cor", length = 50)
    private String cor;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "materia_prima", length = 100)
    private String materiaPrima;

    // relação com filial
    @JsonBackReference("filial-materiais")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_filial", nullable = false,
            foreignKey = @ForeignKey(name = "fk_material_filial"))
    private FilialEntity filial;
}
