package com.centrointegral.backend.repository;

import com.centrointegral.backend.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    Optional<Profesional> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}