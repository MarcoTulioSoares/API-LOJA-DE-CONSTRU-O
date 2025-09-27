package com.example.demo.api.service;

import com.example.demo.api.dto.FerramentaDTO;
import com.example.demo.api.dto.FilialResumoDTO;
import com.example.demo.api.model.FerramentaEntity;
import com.example.demo.api.model.FilialEntity;
import com.example.demo.api.repository.FerramentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FerramentaService {

    private final FerramentaRepository ferramentaRepository;

    @Transactional(readOnly = true)
    public List<FerramentaDTO> listarTodos() {
        return ferramentaRepository.findAll()
                .stream()
                .map(this::mapearParaDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<FerramentaDTO> buscarPorId(Integer id) {
        return ferramentaRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    private FerramentaDTO mapearParaDTO(FerramentaEntity entity) {
        if (entity == null) {
            return null;
        }

        return FerramentaDTO.builder()
                .codFerramenta(entity.getCodFerramenta())
                .codigoProduto(entity.getCodigoProduto())
                .valor(entity.getValor())
                .marca(entity.getMarca())
                .nome(entity.getNome())
                .qtdPacote(entity.getQtdPacote())
                .filial(mapearFilialResumo(entity.getFilial()))
                .build();
    }

    private FilialResumoDTO mapearFilialResumo(FilialEntity filial) {
        if (filial == null) {
            return null;
        }

        return FilialResumoDTO.builder()
                .idLancamento(filial.getIdLancamento())
                .nome(filial.getNome())
                .build();
    }
}
