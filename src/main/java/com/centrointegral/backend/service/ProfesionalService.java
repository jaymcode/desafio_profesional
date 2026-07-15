package com.centrointegral.backend.service;

import com.centrointegral.backend.dto.ProfesionalRequestDTO;
import com.centrointegral.backend.dto.ProfesionalResponseDTO;
import com.centrointegral.backend.entity.Profesional;
import com.centrointegral.backend.exception.DuplicateProfesionalException;
import com.centrointegral.backend.exception.InvalidProfesionalException;
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

    public List<ProfesionalResponseDTO> getAllProfesionales() {
        return profesionalRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Page<ProfesionalResponseDTO> getProfesionalesPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return profesionalRepository.findAll(pageable).map(this::toResponseDTO);
    }

    public List<ProfesionalResponseDTO> getRandomProfesionales(int limit) {
        List<Profesional> all = profesionalRepository.findAll();
        if (all.size() <= limit) {
            return all.stream().map(this::toResponseDTO).toList();
        }
        Random random = new Random();
        List<Profesional> randomList = new java.util.ArrayList<>();
        while (randomList.size() < limit) {
            Profesional p = all.get(random.nextInt(all.size()));
            if (!randomList.contains(p)) {
                randomList.add(p);
            }
        }
        return randomList.stream().map(this::toResponseDTO).toList();
    }

    public Optional<ProfesionalResponseDTO> getProfesionalById(Long id) {
        return profesionalRepository.findById(id).map(this::toResponseDTO);
    }

    /**
     * Save a profesional with input validation and sanitization
     * Prevents XSS, image injection, and invalid data
     */
    public ProfesionalResponseDTO saveProfesional(ProfesionalRequestDTO requestDTO) {
        // Validate all required fields
        if (!ValidationUtil.isValidNombre(requestDTO.getNombre())) {
            throw new InvalidProfesionalException("El nombre debe tener entre 1 y 255 caracteres");
        }

        if (!ValidationUtil.isValidDescripcion(requestDTO.getDescripcion())) {
            throw new InvalidProfesionalException("La descripción debe tener entre 1 y 5000 caracteres");
        }

        if (!ValidationUtil.isValidProfession(requestDTO.getProfesion())) {
            throw new InvalidProfesionalException("La profesión debe ser una de: Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía, Pediatría");
        }

        // Check for duplicate nombre
        if (profesionalRepository.existsByNombre(requestDTO.getNombre())) {
            throw new DuplicateProfesionalException("Ya existe un profesional con el nombre: " + requestDTO.getNombre());
        }

        // Sanitize HTML content to prevent XSS
        String sanitizedNombre = ValidationUtil.sanitizeInput(requestDTO.getNombre());
        String sanitizedDescripcion = ValidationUtil.sanitizeInput(requestDTO.getDescripcion());

        // Validate and filter image URLs
        List<String> validatedImagenes = ValidationUtil.validateImageUrls(requestDTO.getImagenes());

        Profesional profesional = new Profesional();
        profesional.setNombre(sanitizedNombre);
        profesional.setDescripcion(sanitizedDescripcion);
        profesional.setProfesion(requestDTO.getProfesion());
        profesional.setImagenes(validatedImagenes);

        return toResponseDTO(profesionalRepository.save(profesional));
    }

    public void deleteProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }

    private ProfesionalResponseDTO toResponseDTO(Profesional profesional) {
        return new ProfesionalResponseDTO(
                profesional.getId(),
                profesional.getNombre(),
                profesional.getDescripcion(),
                profesional.getProfesion(),
                profesional.getImagenes()
        );
    }
}