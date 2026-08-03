package com.centrointegral.backend.service;

import com.centrointegral.backend.dto.LoginRequestDTO;
import com.centrointegral.backend.dto.UsuarioRegistrationRequestDTO;
import com.centrointegral.backend.dto.UsuarioResponseDTO;
import com.centrointegral.backend.entity.Usuario;
import com.centrointegral.backend.exception.DuplicateUsuarioException;
import com.centrointegral.backend.exception.InvalidUsuarioException;
import com.centrointegral.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UsuarioService Unit Tests")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioRegistrationRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new UsuarioRegistrationRequestDTO();
        requestDTO.setNombre("Ana");
        requestDTO.setApellido("García");
        requestDTO.setEmail("ana@example.com");
        requestDTO.setPassword("Password123!");
    }

    @Test
    @DisplayName("Should register a user successfully when email is unique")
    void shouldRegisterUserSuccessfullyWhenEmailIsUnique() {
        when(usuarioRepository.existsByEmail("ana@example.com")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId(1L);
            return usuario;
        });

        UsuarioResponseDTO result = usuarioService.register(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("ana@example.com");
        assertThat(result.getNombre()).isEqualTo("Ana");
        verify(usuarioRepository).existsByEmail("ana@example.com");
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Should throw exception when email already exists")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(usuarioRepository.existsByEmail("ana@example.com")).thenReturn(true);

        assertThatThrownBy(() -> usuarioService.register(requestDTO))
                .isInstanceOf(DuplicateUsuarioException.class)
                .hasMessageContaining("correo electrónico");
        verify(usuarioRepository).existsByEmail("ana@example.com");
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Should reject invalid password length")
    void shouldRejectInvalidPasswordLength() {
        requestDTO.setPassword("abc");

        assertThatThrownBy(() -> usuarioService.register(requestDTO))
                .isInstanceOf(InvalidUsuarioException.class)
                .hasMessageContaining("contraseña");
    }

    @Test
    @DisplayName("Should log in a user with valid credentials")
    void shouldLoginUserWithValidCredentials() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("ana@example.com");
        loginRequestDTO.setPassword("Password123!");

        Usuario usuario = new Usuario("Ana", "García", "ana@example.com", "Password123!");
        usuario.setId(1L);
        when(usuarioRepository.findByEmail("ana@example.com")).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO result = usuarioService.login(loginRequestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("ana@example.com");
        assertThat(result.getNombre()).isEqualTo("Ana");
        verify(usuarioRepository).findByEmail("ana@example.com");
    }

    @Test
    @DisplayName("Should reject login with invalid credentials")
    void shouldRejectLoginWithInvalidCredentials() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("ana@example.com");
        loginRequestDTO.setPassword("WrongPassword123!");

        when(usuarioRepository.findByEmail("ana@example.com")).thenReturn(Optional.of(new Usuario("Ana", "García", "ana@example.com", "Password123!")));

        assertThatThrownBy(() -> usuarioService.login(loginRequestDTO))
                .isInstanceOf(InvalidUsuarioException.class)
                .hasMessageContaining("incorrectos");
    }
}
