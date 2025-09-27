package com.example.demo.api.controller;

import com.example.demo.api.dto.MaterialConstrucaoDTO;
import com.example.demo.api.service.MaterialConstrucaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/MATERIAL-CONSTRUCAO")
@RequiredArgsConstructor
public class MaterialConstrucaoController {

    private final MaterialConstrucaoService materialConstrucaoService;

    @GetMapping
    public ResponseEntity<List<MaterialConstrucaoDTO>> listarTodos() {
        return ResponseEntity.ok(materialConstrucaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialConstrucaoDTO> buscarPorId(@PathVariable Integer id) {
        return materialConstrucaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
