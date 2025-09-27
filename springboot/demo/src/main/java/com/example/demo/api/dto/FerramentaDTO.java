package com.example.demo.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FerramentaDTO {
    private Integer codFerramenta;
    private String codigoProduto;
    private Double valor;
    private String marca;
    private String nome;
    private Integer qtdPacote;

    private FilialResumoDTO filial; // referência à filial (resumida)
}
