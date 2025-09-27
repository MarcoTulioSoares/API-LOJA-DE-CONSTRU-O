package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "TB_FERRAMENTA")
public class FerramentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_FERRAMENTA")
    private Integer codFerramenta;

    @Column(name = "CODIGO_PRODUTO", length = 50, nullable = false)
    private String codigoProduto;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "MARCA", length = 100)
    private String marca;

    @Column(name = "NOME", length = 150, nullable = false)
    private String nome;

    @Column(name = "QTD_PACOTE")
    private Integer qtdPacote;


    @JsonBackReference("filial-ferramentas")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CODIGO_FILIAL", nullable = false,
            foreignKey = @ForeignKey(name = "FK_FERRAMENTA_FILIAL"))
    private FilialEntity filial;
}
