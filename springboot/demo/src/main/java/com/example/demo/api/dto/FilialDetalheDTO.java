package com.example.demo.api.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilialDetalheDTO {
    private Integer idLancamento;
    private String nome;

    private List<FerramentaDTO> ferramentas;
    private List<MaterialConstrucaoDTO> materiais;
}
