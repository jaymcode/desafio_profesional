package com.centrointegral.backend.service;

import com.centrointegral.backend.entity.Profesional;
import com.centrointegral.backend.repository.ProfesionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProfesionalService Unit Tests")
class ProfesionalServiceTest {

    @Mock
    private ProfesionalRepository profesionalRepository;

    @InjectMocks
    private ProfesionalService profesionalService;

    private Profesional profesional;
    private List<String> imagenes;

    @BeforeEach
    void setUp() {
        imagenes = Arrays.asList("image1.jpg", "image2.jpg");
        profesional = new Profesional("Dr. Juan Perez", "Cardiólogo especialista", "Cardiología", imagenes);
        profesional.setId(1L);
    }

    @Test
    @DisplayName("Should retrieve all professionals successfully")
    void testGetAllProfesionales() {
        // Arrange
        List<Profesional> profesionales = Arrays.asList(
                profesional,
                new Profesional("Dra. Maria Gonzalez", "Pediatra", "Pediatría", imagenes)
        );
        when(profesionalRepository.findAll()).thenReturn(profesionales);

        // Act
        List<Profesional> result = profesionalService.getAllProfesionales();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .contains(profesional);
        verify(profesionalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should retrieve empty list when no professionals exist")
    void testGetAllProfesionalesEmpty() {
        // Arrange
        when(profesionalRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Profesional> result = profesionalService.getAllProfesionales();

        // Assert
        assertThat(result).isNotNull().isEmpty();
        verify(profesionalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should retrieve professionals with pagination")
    void testGetProfesionalesPaged() {
        // Arrange
        List<Profesional> content = Arrays.asList(profesional);
        Page<Profesional> page = new PageImpl<>(content, PageRequest.of(0, 10), 1);
        when(profesionalRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Act
        Page<Profesional> result = profesionalService.getProfesionalesPaged(0, 10);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1).contains(profesional);
        assertThat(result.getNumber()).isZero();
        assertThat(result.getSize()).isEqualTo(10);
        verify(profesionalRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Should retrieve professional by ID successfully")
    void testGetProfesionalById() {
        // Arrange
        when(profesionalRepository.findById(1L)).thenReturn(Optional.of(profesional));

        // Act
        Optional<Profesional> result = profesionalService.getProfesionalById(1L);

        // Assert
        assertThat(result)
                .isPresent()
                .contains(profesional);
        verify(profesionalRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should return empty Optional when professional not found")
    void testGetProfesionalByIdNotFound() {
        // Arrange
        when(profesionalRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Profesional> result = profesionalService.getProfesionalById(999L);

        // Assert
        assertThat(result).isEmpty();
        verify(profesionalRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Should save professional successfully when name is unique")
    void testSaveProfesionalSuccess() throws Exception {
        // Arrange
        when(profesionalRepository.existsByNombre("Dr. Juan Perez")).thenReturn(false);
        when(profesionalRepository.save(profesional)).thenReturn(profesional);

        // Act
        Profesional result = profesionalService.saveProfesional(profesional);

        // Assert
        assertThat(result)
                .isNotNull()
                .isEqualTo(profesional);
        verify(profesionalRepository, times(1)).existsByNombre("Dr. Juan Perez");
        verify(profesionalRepository, times(1)).save(profesional);
    }

    @Test
    @DisplayName("Should throw exception when saving professional with duplicate name")
    void testSaveProfesionalDuplicateName() {
        // Arrange
        when(profesionalRepository.existsByNombre("Dr. Juan Perez")).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> profesionalService.saveProfesional(profesional))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Ya existe un profesional con el nombre");
        verify(profesionalRepository, times(1)).existsByNombre("Dr. Juan Perez");
        verify(profesionalRepository, never()).save(profesional);
    }

    @Test
    @DisplayName("Should delete professional by ID")
    void testDeleteProfesional() {
        // Act
        profesionalService.deleteProfesional(1L);

        // Assert
        verify(profesionalRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should return random professionals when limit is less than total")
    void testGetRandomProfesionales() {
        // Arrange
        List<Profesional> allProfesionales = Arrays.asList(
                profesional,
                new Profesional("Dra. Maria Gonzalez", "Pediatra", "Pediatría", imagenes),
                new Profesional("Dr. Carlos Lopez", "Neurólogo", "Neurología", imagenes),
                new Profesional("Dra. Ana Martínez", "Dermatóloga", "Dermatología", imagenes)
        );
        when(profesionalRepository.findAll()).thenReturn(allProfesionales);

        // Act
        List<Profesional> result = profesionalService.getRandomProfesionales(2);

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .allMatch(p -> allProfesionales.contains(p));
        verify(profesionalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return all professionals when limit is greater than or equal to total")
    void testGetRandomProfesionalesLimitGreaterThanTotal() {
        // Arrange
        List<Profesional> allProfesionales = Arrays.asList(
                profesional,
                new Profesional("Dra. Maria Gonzalez", "Pediatra", "Pediatría", imagenes)
        );
        when(profesionalRepository.findAll()).thenReturn(allProfesionales);

        // Act
        List<Profesional> result = profesionalService.getRandomProfesionales(10);

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrderElementsOf(allProfesionales);
        verify(profesionalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list for random professionals when no professionals exist")
    void testGetRandomProfesionalesEmpty() {
        // Arrange
        when(profesionalRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Profesional> result = profesionalService.getRandomProfesionales(5);

        // Assert
        assertThat(result).isNotNull().isEmpty();
        verify(profesionalRepository, times(1)).findAll();
    }
}
