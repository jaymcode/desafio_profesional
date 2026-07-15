package com.centrointegral.backend.dto;

import java.util.List;

public class ProfesionalResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String profesion;
    private List<String> imagenes;

    public ProfesionalResponseDTO() {
    }

    public ProfesionalResponseDTO(Long id, String nombre, String descripcion, String profesion, List<String> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesion = profesion;
        this.imagenes = imagenes;
    }

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
