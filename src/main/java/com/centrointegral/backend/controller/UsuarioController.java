package com.centrointegral.backend.controller;

import com.centrointegral.backend.dto.LoginRequestDTO;
import com.centrointegral.backend.dto.UsuarioRegistrationRequestDTO;
import com.centrointegral.backend.dto.UsuarioResponseDTO;
import com.centrointegral.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173", "http://localhost:5174", "http://127.0.0.1:5174", "http://localhost:5175", "http://127.0.0.1:5175", "http://localhost:5176", "http://127.0.0.1:5176", "http://localhost:5177", "http://127.0.0.1:5177", "http://localhost:5178", "http://127.0.0.1:5178", "http://localhost:3000", "http://127.0.0.1:3000"}, allowCredentials = "true")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody UsuarioRegistrationRequestDTO requestDTO) {
        UsuarioResponseDTO response = usuarioService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        UsuarioResponseDTO response = usuarioService.login(requestDTO);
        return ResponseEntity.ok(response);
    }
}
