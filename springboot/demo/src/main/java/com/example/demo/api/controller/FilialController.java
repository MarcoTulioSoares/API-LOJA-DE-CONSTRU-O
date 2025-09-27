package com.example.demo.api.controller;

import com.example.demo.api.dto.FilialDetalheDTO;
import com.example.demo.api.dto.FilialResumoDTO;
import com.example.demo.api.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/FILIAL")
@RequiredArgsConstructor
public class FilialController {

    private final FilialService filialService;

    @GetMapping
    public ResponseEntity<List<FilialResumoDTO>> listarTodas() {
        return ResponseEntity.ok(filialService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialDetalheDTO> buscarPorId(@PathVariable Integer id) {
        return filialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FilialDetalheDTO> criar(@RequestBody FilialResumoDTO dto) {
        FilialDetalheDTO criado = filialService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialDetalheDTO> atualizar(@PathVariable Integer id,
                                                      @RequestBody FilialResumoDTO dto) {
        return filialService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removido = filialService.deletar(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
