package com.centrointegral.backend.service;

import com.centrointegral.backend.entity.Profesional;
import com.centrointegral.backend.repository.ProfesionalRepository;
import com.centrointegral.backend.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    public List<Profesional> getAllProfesionales() {
        return profesionalRepository.findAll();
    }

    public Page<Profesional> getProfesionalesPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return profesionalRepository.findAll(pageable);
    }

    public List<Profesional> getRandomProfesionales(int limit) {
        List<Profesional> all = profesionalRepository.findAll();
        if (all.size() <= limit) {
            return all;
        }
        Random random = new Random();
        List<Profesional> randomList = new java.util.ArrayList<>();
        while (randomList.size() < limit) {
            Profesional p = all.get(random.nextInt(all.size()));
            if (!randomList.contains(p)) {
                randomList.add(p);
            }
        }
        return randomList;
    }

    public Optional<Profesional> getProfesionalById(Long id) {
        return profesionalRepository.findById(id);
    }

    /**
     * Save a profesional with input validation and sanitization
     * Prevents XSS, image injection, and invalid data
     */
    public Profesional saveProfesional(Profesional profesional) throws Exception {
        // Validate all required fields
        if (!ValidationUtil.isValidNombre(profesional.getNombre())) {
            throw new IllegalArgumentException("El nombre debe tener entre 1 y 255 caracteres");
        }

        if (!ValidationUtil.isValidDescripcion(profesional.getDescripcion())) {
            throw new IllegalArgumentException("La descripción debe tener entre 1 y 5000 caracteres");
        }

        if (!ValidationUtil.isValidProfession(profesional.getProfesion())) {
            throw new IllegalArgumentException("La profesión debe ser una de: Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía, Pediatría");
        }

        // Check for duplicate nombre
        if (profesionalRepository.existsByNombre(profesional.getNombre())) {
            throw new Exception("Ya existe un profesional con el nombre: " + profesional.getNombre());
        }

        // Sanitize HTML content to prevent XSS
        String sanitizedNombre = ValidationUtil.sanitizeInput(profesional.getNombre());
        String sanitizedDescripcion = ValidationUtil.sanitizeInput(profesional.getDescripcion());

        // Validate and filter image URLs
        List<String> validatedImagenes = ValidationUtil.validateImageUrls(profesional.getImagenes());

        // Set sanitized and validated data
        profesional.setNombre(sanitizedNombre);
        profesional.setDescripcion(sanitizedDescripcion);
        profesional.setImagenes(validatedImagenes);

        return profesionalRepository.save(profesional);
    }

    public void deleteProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }
}