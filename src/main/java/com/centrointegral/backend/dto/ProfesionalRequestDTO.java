package com.centrointegral.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ProfesionalRequestDTO {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es requerida")
    @Size(min = 1, max = 5000, message = "La descripción debe tener entre 1 y 5000 caracteres")
    private String descripcion;

    @NotBlank(message = "La profesión es requerida")
    @Size(min = 1, max = 255, message = "La profesión debe tener entre 1 y 255 caracteres")
    @Pattern(regexp = "^(Kinesiología|Fisiatría|Fonoaudiología|Psicopedagogía|Pediatría)$",
            message = "La profesión debe ser una de: Kinesiología, Fisiatría, Fonoaudiología, Psicopedagogía, Pediatría")
    private String profesion;

    @NotEmpty(message = "Debe incluir al menos una imagen")
    private List<String> imagenes;

    public ProfesionalRequestDTO() {
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
