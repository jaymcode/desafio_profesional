package com.centrointegral.backend;

import com.centrointegral.backend.entity.Usuario;
import com.centrointegral.backend.repository.ProfesionalRepository;
import com.centrointegral.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DataLoader tests")
class DataLoaderTest {

    @Mock
    private ProfesionalRepository profesionalRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    @DisplayName("Should create a demo user when no users exist")
    void shouldCreateDemoUserWhenNoUsersExist() throws Exception {
        when(profesionalRepository.count()).thenReturn(1L);
        when(usuarioRepository.count()).thenReturn(0L);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        dataLoader.run();

        verify(usuarioRepository).save(any(Usuario.class));
    }
}
