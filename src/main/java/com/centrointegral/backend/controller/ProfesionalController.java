package com.centrointegral.backend.controller;

import com.centrointegral.backend.entity.Profesional;
import com.centrointegral.backend.service.ProfesionalService;
import com.centrointegral.backend.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesionales")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping
    public ResponseEntity<List<Profesional>> getAllProfesionales() {
        List<Profesional> profesionales = profesionalService.getAllProfesionales();
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Profesional>> getProfesionalesPaged(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Page<Profesional> profesionales = profesionalService.getProfesionalesPaged(page, size);
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Profesional>> getRandomProfesionales(@RequestParam(defaultValue = "10") int limit) {
        List<Profesional> profesionales = profesionalService.getRandomProfesionales(limit);
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesional> getProfesionalById(@PathVariable Long id) {
        Optional<Profesional> profesional = profesionalService.getProfesionalById(id);
        return profesional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProfesional(@Valid @RequestBody Profesional profesional) {
        try {
            Profesional saved = profesionalService.saveProfesional(profesional);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            // Validation errors - 400 Bad Request
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            // Business logic errors (e.g., duplicate nombre) - 409 Conflict
            if (e.getMessage().contains("Ya existe")) {
                ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }
            // Generic error - 500 Internal Server Error
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al procesar la solicitud. Por favor, intenta de nuevo.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesional(@PathVariable Long id) {
        try {
            profesionalService.deleteProfesional(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al eliminar el profesional. Por favor, intenta de nuevo.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}