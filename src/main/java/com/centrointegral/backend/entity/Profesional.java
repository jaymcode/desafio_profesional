package com.centrointegral.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "profesionales")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    @Column(unique = true)
    private String nombre;

    @NotBlank(message = "La descripción es requerida")
    @Size(min = 1, max = 5000, message = "La descripción debe tener entre 1 y 5000 caracteres")
    private String descripcion;

    @NotBlank(message = "La profesión es requerida")
    private String profesion;

    @ElementCollection
    @CollectionTable(name = "profesional_imagenes", joinColumns = @JoinColumn(name = "profesional_id"))
    @Column(name = "imagen")
    private List<String> imagenes;

    // Constructores
    public Profesional() {}

    public Profesional(String nombre, String descripcion, String profesion, List<String> imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesion = profesion;
        this.imagenes = imagenes;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
}