package com.centrointegral.backend.controller;

import com.centrointegral.backend.dto.ProfesionalRequestDTO;
import com.centrointegral.backend.dto.ProfesionalResponseDTO;
import com.centrointegral.backend.service.ProfesionalService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesionales")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173", "http://localhost:5174", "http://127.0.0.1:5174", "http://localhost:5175", "http://127.0.0.1:5175", "http://localhost:5176", "http://127.0.0.1:5176", "http://localhost:5177", "http://127.0.0.1:5177", "http://localhost:5178", "http://127.0.0.1:5178", "http://localhost:3000", "http://127.0.0.1:3000"}, allowCredentials = "true")
public class ProfesionalController {

    private final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesionalResponseDTO>> getAllProfesionales() {
        List<ProfesionalResponseDTO> profesionales = profesionalService.getAllProfesionales();
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ProfesionalResponseDTO>> getProfesionalesPaged(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Page<ProfesionalResponseDTO> profesionales = profesionalService.getProfesionalesPaged(page, size);
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/random")
    public ResponseEntity<List<ProfesionalResponseDTO>> getRandomProfesionales(@RequestParam(defaultValue = "10") int limit) {
        List<ProfesionalResponseDTO> profesionales = profesionalService.getRandomProfesionales(limit);
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalResponseDTO> getProfesionalById(@PathVariable Long id) {
        Optional<ProfesionalResponseDTO> profesional = profesionalService.getProfesionalById(id);
        return profesional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfesionalResponseDTO> createProfesional(@Valid @RequestBody ProfesionalRequestDTO requestDTO) {
        ProfesionalResponseDTO saved = profesionalService.saveProfesional(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesional(@PathVariable Long id) {
        profesionalService.deleteProfesional(id);
        return ResponseEntity.noContent().build();
    }
}