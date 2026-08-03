package com.centrointegral.backend.service;

import com.centrointegral.backend.dto.LoginRequestDTO;
import com.centrointegral.backend.dto.UsuarioRegistrationRequestDTO;
import com.centrointegral.backend.dto.UsuarioResponseDTO;
import com.centrointegral.backend.entity.Usuario;
import com.centrointegral.backend.exception.DuplicateUsuarioException;
import com.centrointegral.backend.exception.InvalidUsuarioException;
import com.centrointegral.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO register(UsuarioRegistrationRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new InvalidUsuarioException("Los datos de registro son obligatorios");
        }

        if (requestDTO.getNombre() == null || requestDTO.getNombre().trim().length() < 2 || requestDTO.getNombre().trim().length() > 50) {
            throw new InvalidUsuarioException("El nombre debe tener entre 2 y 50 caracteres");
        }

        if (requestDTO.getApellido() == null || requestDTO.getApellido().trim().length() < 2 || requestDTO.getApellido().trim().length() > 50) {
            throw new InvalidUsuarioException("El apellido debe tener entre 2 y 50 caracteres");
        }

        if (requestDTO.getEmail() == null || !requestDTO.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidUsuarioException("El correo electrónico debe tener un formato válido");
        }

        if (requestDTO.getPassword() == null || requestDTO.getPassword().length() < 8 || requestDTO.getPassword().length() > 100) {
            throw new InvalidUsuarioException("La contraseña debe tener entre 8 y 100 caracteres");
        }

        if (!requestDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$")) {
            throw new InvalidUsuarioException("La contraseña debe incluir mayúscula, minúscula, número y símbolo");
        }

        if (usuarioRepository.existsByEmail(requestDTO.getEmail().trim().toLowerCase())) {
            throw new DuplicateUsuarioException("Ya existe un usuario registrado con ese correo electrónico");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre().trim());
        usuario.setApellido(requestDTO.getApellido().trim());
        usuario.setEmail(requestDTO.getEmail().trim().toLowerCase());
        usuario.setPassword(requestDTO.getPassword());

        Usuario saved = usuarioRepository.save(usuario);
        return toResponse(saved);
    }

    public UsuarioResponseDTO login(LoginRequestDTO requestDTO) {
        if (requestDTO == null || requestDTO.getEmail() == null || requestDTO.getPassword() == null) {
            throw new InvalidUsuarioException("Por favor, ingresa tu correo y contraseña");
        }

        String email = requestDTO.getEmail().trim().toLowerCase();
        if (email.isEmpty()) {
            throw new InvalidUsuarioException("Por favor, ingresa tu correo y contraseña");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidUsuarioException("Correo o contraseña incorrectos"));

        if (!requestDTO.getPassword().equals(usuario.getPassword())) {
            throw new InvalidUsuarioException("Correo o contraseña incorrectos");
        }

        return toResponse(usuario);
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
    }
}
