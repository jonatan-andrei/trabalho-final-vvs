package com.seguranca.demonstracao.service;

import com.seguranca.demonstracao.domain.Cliente;
import com.seguranca.demonstracao.repository.ClienteRepository;
import com.seguranca.demonstracao.repository.UsuarioRepository;
import com.seguranca.demonstracao.service.impl.ClienteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Cliente> clienteCaptor;

    @Test
    public void cadastrarComSucesso() {
        // Arrange
        String email = "jonatan@jonatan.com";
        String senha = "abcd1234";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        clienteService.cadastrar(email, senha);

        // Assert
        Mockito.verify(clienteRepository).save(clienteCaptor.capture());
        Mockito.verify(passwordEncoder).encode(senha);
        Cliente cliente = clienteCaptor.getValue();
        assertEquals(cliente.getEmail(), email);
    }
}
