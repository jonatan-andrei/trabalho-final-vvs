package com.seguranca.demonstracao.service;

import com.seguranca.demonstracao.domain.Vendedor;
import com.seguranca.demonstracao.repository.UsuarioRepository;
import com.seguranca.demonstracao.repository.VendedorRepository;
import com.seguranca.demonstracao.service.impl.VendedorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendedorServiceTest {

    @InjectMocks
    private VendedorServiceImpl vendedorService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Vendedor> vendedorCaptor;

    @Test
    public void cadastrarComSucesso() {
        // Arrange
        String email = "jonatan@jonatan.com";
        String senha = "abcd1234";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        vendedorService.cadastrar(email, senha);

        // Assert
        Mockito.verify(vendedorRepository).save(vendedorCaptor.capture());
        Mockito.verify(passwordEncoder).encode(senha);
        Vendedor vendedor = vendedorCaptor.getValue();
        assertEquals(vendedor.getEmail(), email);
    }
}
