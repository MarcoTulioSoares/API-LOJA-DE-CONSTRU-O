package com.example.demo.api.service;

import com.example.demo.api.dto.FilialResumoDTO;
import com.example.demo.api.dto.MaterialConstrucaoDTO;
import com.example.demo.api.model.FilialEntity;
import com.example.demo.api.model.MaterialConstrucaoEntity;
import com.example.demo.api.repository.MaterialConstrucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialConstrucaoService {

    private final MaterialConstrucaoRepository materialConstrucaoRepository;

    @Transactional(readOnly = true)
    public List<MaterialConstrucaoDTO> listarTodos() {
        return materialConstrucaoRepository.findAll()
                .stream()
                .map(this::mapearParaDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<MaterialConstrucaoDTO> buscarPorId(Integer id) {
        return materialConstrucaoRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    private MaterialConstrucaoDTO mapearParaDTO(MaterialConstrucaoEntity entity) {
        if (entity == null) {
            return null;
        }

        return MaterialConstrucaoDTO.builder()
                .codMaterial(entity.getCodMaterial())
                .codigoProduto(entity.getCodigoProduto())
                .valor(entity.getValor())
                .cor(entity.getCor())
                .nome(entity.getNome())
                .materiaPrima(entity.getMateriaPrima())
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
