package com.example.demo.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialConstrucaoDTO {
    private Integer codMaterial;
    private String codigoProduto;
    private Double valor;
    private String cor;
    private String nome;
    private String materiaPrima;

    private FilialResumoDTO filial; // referência à filial (resumida)
}
