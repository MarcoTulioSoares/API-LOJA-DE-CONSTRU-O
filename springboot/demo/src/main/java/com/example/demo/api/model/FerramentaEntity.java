package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_ferramenta")
public class FerramentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_ferramenta", nullable = false, updatable = false,
            columnDefinition = "INT AUTO_INCREMENT")
    private Integer codFerramenta;

    @Column(name = "codigo_produto", length = 50, nullable = false)
    private String codigoProduto;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "qtd_pacote")
    private Integer qtdPacote;


    @JsonBackReference("filial-ferramentas")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_filial", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ferramenta_filial"))
    private FilialEntity filial;
}
