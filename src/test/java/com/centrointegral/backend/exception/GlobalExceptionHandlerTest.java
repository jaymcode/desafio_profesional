package com.centrointegral.backend.exception;

import com.centrointegral.backend.controller.ProfesionalController;
import com.centrointegral.backend.entity.Profesional;
import com.centrointegral.backend.util.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Should map duplicate professional exceptions to conflict responses")
    void shouldMapDuplicateProfessionalExceptionToConflict() {
        ResponseEntity<ErrorResponse> response = handler.handleDuplicateProfesionalException(
                new DuplicateProfesionalException("Ya existe un profesional con el nombre: Dr. Juan")
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains("Ya existe un profesional");
    }

    @Test
    @DisplayName("Should map bean validation errors to bad request responses")
    void shouldMapMethodArgumentValidationErrorsToBadRequest() throws NoSuchMethodException {
        MethodParameter parameter = new MethodParameter(
                ProfesionalController.class.getDeclaredMethod("createProfesional", com.centrointegral.backend.dto.ProfesionalRequestDTO.class),
                0
        );
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Profesional(), "profesional");
        bindingResult.rejectValue("nombre", "NotBlank", "El nombre es requerido");

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);
        ResponseEntity<ErrorResponse> response = handler.handleMethodArgumentNotValidException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("El nombre es requerido");
    }
}
