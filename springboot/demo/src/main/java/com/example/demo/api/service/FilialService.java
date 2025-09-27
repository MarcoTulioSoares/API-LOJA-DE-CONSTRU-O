package com.example.demo.api.service;

import com.example.demo.api.dto.FerramentaDTO;
import com.example.demo.api.dto.FilialDetalheDTO;
import com.example.demo.api.dto.FilialResumoDTO;
import com.example.demo.api.dto.MaterialConstrucaoDTO;
import com.example.demo.api.model.FerramentaEntity;
import com.example.demo.api.model.FilialEntity;
import com.example.demo.api.model.MaterialConstrucaoEntity;
import com.example.demo.api.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;

    @Transactional(readOnly = true)
    public List<FilialResumoDTO> listarTodas() {
        return filialRepository.findAll()
                .stream()
                .map(this::mapearParaResumo)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<FilialDetalheDTO> buscarPorId(Integer id) {
        return filialRepository.findById(id)
                .map(this::mapearParaDetalhe);
    }

    @Transactional
    public FilialDetalheDTO criar(FilialResumoDTO dto) {
        FilialEntity entity = new FilialEntity();
        atualizarCampos(entity, dto);
        FilialEntity salvo = filialRepository.save(entity);
        return mapearParaDetalhe(salvo);
    }

    @Transactional
    public Optional<FilialDetalheDTO> atualizar(Integer id, FilialResumoDTO dto) {
        return filialRepository.findById(id)
                .map(entity -> {
                    atualizarCampos(entity, dto);
                    FilialEntity salvo = filialRepository.save(entity);
                    return mapearParaDetalhe(salvo);
                });
    }

    @Transactional
    public boolean deletar(Integer id) {
        if (!filialRepository.existsById(id)) {
            return false;
        }
        filialRepository.deleteById(id);
        return true;
    }

    private void atualizarCampos(FilialEntity entity, FilialResumoDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da filial são obrigatórios");
        }

        Integer dtoId = normalizarId(dto.getIdLancamento());

        if (entity.getIdLancamento() == null) {
            if (dtoId != null) {
                entity.setIdLancamento(dtoId);
            }
        } else if (dtoId != null && !entity.getIdLancamento().equals(dtoId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é permitido alterar o código da filial");
        }

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da filial é obrigatório");
        }

        entity.setNome(dto.getNome());
    }

    private Integer normalizarId(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return id;
    }

    private FilialResumoDTO mapearParaResumo(FilialEntity entity) {
        if (entity == null) {
            return null;
        }

        return FilialResumoDTO.builder()
                .idLancamento(entity.getIdLancamento())
                .nome(entity.getNome())
                .build();
    }

    private FilialDetalheDTO mapearParaDetalhe(FilialEntity entity) {
        if (entity == null) {
            return null;
        }

        List<FerramentaDTO> ferramentas = Optional.ofNullable(entity.getFerramentas())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapearFerramenta)
                .toList();

        List<MaterialConstrucaoDTO> materiais = Optional.ofNullable(entity.getMateriais())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapearMaterial)
                .toList();

        return FilialDetalheDTO.builder()
                .idLancamento(entity.getIdLancamento())
                .nome(entity.getNome())
                .ferramentas(ferramentas)
                .materiais(materiais)
                .build();
    }

    private FerramentaDTO mapearFerramenta(FerramentaEntity entity) {
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
                .filial(mapearParaResumo(entity.getFilial()))
                .build();
    }

    private MaterialConstrucaoDTO mapearMaterial(MaterialConstrucaoEntity entity) {
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
                .filial(mapearParaResumo(entity.getFilial()))
                .build();
    }
}
