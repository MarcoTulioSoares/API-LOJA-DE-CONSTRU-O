package com.example.demo.api.service;

import com.example.demo.api.dto.FerramentaDTO;
import com.example.demo.api.dto.FilialDetalheDTO;
import com.example.demo.api.dto.FilialResumoDTO;
import com.example.demo.api.dto.MaterialConstrucaoDTO;
import com.example.demo.api.model.FerramentaEntity;
import com.example.demo.api.model.FilialEntity;
import com.example.demo.api.model.MaterialConstrucaoEntity;
import com.example.demo.api.repository.FilialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilialServiceTest {

    @Mock
    private FilialRepository filialRepository;

    private FilialService service;

    @BeforeEach
    void setUp() {
        service = new FilialService(filialRepository);
    }

    @Test
    @Disabled("Skipped by request")
    void listarTodasDeveRetornarResumoDasFiliais() {
        FilialEntity entity = FilialEntity.builder()
                .idLancamento(3)
                .nome("Loja Centro")
                .build();

        when(filialRepository.findAll()).thenReturn(List.of(entity));

        List<FilialResumoDTO> resultado = service.listarTodas();

        assertThat(resultado)
                .singleElement()
                .satisfies(resumo -> {
                    assertThat(resumo.getIdLancamento()).isEqualTo(3);
                    assertThat(resumo.getNome()).isEqualTo("Loja Centro");
                });
    }

    @Test
    @Disabled("Skipped by request")
    void buscarPorIdDeveRetornarDetalhesComAssociacoes() {
        FilialEntity filial = FilialEntity.builder()
                .idLancamento(5)
                .nome("Loja Norte")
                .build();

        FerramentaEntity ferramenta = FerramentaEntity.builder()
                .codFerramenta(11)
                .codigoProduto("FER-010")
                .valor(79.9)
                .marca("ACME")
                .nome("Furadeira")
                .qtdPacote(1)
                .filial(filial)
                .build();

        MaterialConstrucaoEntity material = MaterialConstrucaoEntity.builder()
                .codMaterial(12)
                .codigoProduto("MAT-010")
                .valor(24.5)
                .cor("Cinza")
                .nome("Cimento")
                .materiaPrima("Calcário")
                .filial(filial)
                .build();

        filial.setFerramentas(List.of(ferramenta));
        filial.setMateriais(List.of(material));

        when(filialRepository.findById(5)).thenReturn(Optional.of(filial));

        Optional<FilialDetalheDTO> resultado = service.buscarPorId(5);

        assertThat(resultado).isPresent();
        FilialDetalheDTO detalhe = resultado.orElseThrow();
        assertThat(detalhe.getNome()).isEqualTo("Loja Norte");
        assertThat(detalhe.getFerramentas())
                .map(FerramentaDTO::getNome)
                .containsExactly("Furadeira");
        assertThat(detalhe.getMateriais())
                .map(MaterialConstrucaoDTO::getNome)
                .containsExactly("Cimento");
    }
}
