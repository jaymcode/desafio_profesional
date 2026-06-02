package com.centrointegral.backend.repository;

import com.centrointegral.backend.entity.Profesional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("ProfesionalRepository Integration Tests")
class ProfesionalRepositoryTest {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    private Profesional profesional1;
    private Profesional profesional2;
    private List<String> imagenes;

    @BeforeEach
    void setUp() {
        imagenes = Arrays.asList("image1.jpg", "image2.jpg");
        
        profesional1 = new Profesional("Dr. Juan Perez", "Cardiólogo especialista", "Cardiología", imagenes);
        profesional2 = new Profesional("Dra. Maria Gonzalez", "Pediatra experiente", "Pediatría", imagenes);
        
        profesionalRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save a professional successfully")
    void testSaveProfesional() {
        // Act
        Profesional saved = profesionalRepository.save(profesional1);

        // Assert
        assertThat(saved)
                .isNotNull()
                .hasFieldOrPropertyWithValue("nombre", "Dr. Juan Perez")
                .hasFieldOrPropertyWithValue("profesion", "Cardiología");
        assertThat(saved.getId()).isNotNull().isPositive();
    }

    @Test
    @DisplayName("Should retrieve professional by ID")
    void testFindById() {
        // Arrange
        Profesional saved = profesionalRepository.save(profesional1);

        // Act
        Optional<Profesional> found = profesionalRepository.findById(saved.getId());

        // Assert
        assertThat(found)
                .isPresent()
                .hasValueSatisfying(p -> {
                    assertThat(p.getNombre()).isEqualTo("Dr. Juan Perez");
                    assertThat(p.getProfesion()).isEqualTo("Cardiología");
                });
    }

    @Test
    @DisplayName("Should return empty Optional when professional not found")
    void testFindByIdNotFound() {
        // Act
        Optional<Profesional> found = profesionalRepository.findById(999L);

        // Assert
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should retrieve professional by name")
    void testFindByNombre() {
        // Arrange
        profesionalRepository.save(profesional1);

        // Act
        Optional<Profesional> found = profesionalRepository.findByNombre("Dr. Juan Perez");

        // Assert
        assertThat(found)
                .isPresent()
                .hasValueSatisfying(p -> {
                    assertThat(p.getNombre()).isEqualTo("Dr. Juan Perez");
                    assertThat(p.getProfesion()).isEqualTo("Cardiología");
                });
    }

    @Test
    @DisplayName("Should return empty Optional when professional name not found")
    void testFindByNombreNotFound() {
        // Act
        Optional<Profesional> found = profesionalRepository.findByNombre("Non Existent");

        // Assert
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should check if professional exists by name")
    void testExistsByNombre() {
        // Arrange
        profesionalRepository.save(profesional1);

        // Act
        boolean exists = profesionalRepository.existsByNombre("Dr. Juan Perez");

        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return false when professional name does not exist")
    void testExistsByNombreNotExists() {
        // Act
        boolean exists = profesionalRepository.existsByNombre("Non Existent");

        // Assert
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Should retrieve all professionals")
    void testFindAll() {
        // Arrange
        profesionalRepository.save(profesional1);
        profesionalRepository.save(profesional2);

        // Act
        List<Profesional> all = profesionalRepository.findAll();

        // Assert
        assertThat(all)
                .isNotNull()
                .hasSize(2)
                .contains(profesional1, profesional2);
    }

    @Test
    @DisplayName("Should return empty list when no professionals exist")
    void testFindAllEmpty() {
        // Act
        List<Profesional> all = profesionalRepository.findAll();

        // Assert
        assertThat(all).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Should delete professional by ID")
    void testDeleteById() {
        // Arrange
        Profesional saved = profesionalRepository.save(profesional1);
        Long id = saved.getId();

        // Act
        profesionalRepository.deleteById(id);

        // Assert
        Optional<Profesional> found = profesionalRepository.findById(id);
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should persist professional with multiple images")
    void testSaveProfesionalWithMultipleImages() {
        // Arrange
        List<String> multipleImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg");
        Profesional prof = new Profesional("Dr. With Images", "Specialist", "Specialization", multipleImages);

        // Act
        Profesional saved = profesionalRepository.save(prof);

        // Assert
        Optional<Profesional> found = profesionalRepository.findById(saved.getId());
        assertThat(found)
                .isPresent()
                .hasValueSatisfying(p -> {
                    assertThat(p.getImagenes()).hasSize(4);
                    assertThat(p.getImagenes()).contains("img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg");
                });
    }

    @Test
    @DisplayName("Should handle null images collection")
    void testSaveProfesionalWithNullImages() {
        // Arrange
        Profesional prof = new Profesional("Dr. No Images", "Specialist", "Specialization", null);

        // Act
        Profesional saved = profesionalRepository.save(prof);

        // Assert
        Optional<Profesional> found = profesionalRepository.findById(saved.getId());
        assertThat(found)
                .isPresent()
                .hasValueSatisfying(p -> assertThat(p.getImagenes()).isNull());
    }
}
