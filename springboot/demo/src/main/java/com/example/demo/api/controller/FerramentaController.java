package com.example.demo.api.controller;

import com.example.demo.api.dto.FerramentaDTO;
import com.example.demo.api.service.FerramentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/FERRAMENTA")
@RequiredArgsConstructor
public class FerramentaController {

    private final FerramentaService ferramentaService;

    @GetMapping
    public ResponseEntity<List<FerramentaDTO>> listarTodos() {
        return ResponseEntity.ok(ferramentaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FerramentaDTO> buscarPorId(@PathVariable Integer id) {
        return ferramentaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
