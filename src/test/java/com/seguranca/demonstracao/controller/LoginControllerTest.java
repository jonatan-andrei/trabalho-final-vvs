package com.seguranca.demonstracao.controller;

import com.seguranca.demonstracao.common.AbstractConfigurationTests;
import com.seguranca.demonstracao.dto.CadastroRequest;
import com.seguranca.demonstracao.dto.LoginRequest;
import com.seguranca.demonstracao.dto.LoginResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest extends AbstractConfigurationTests {

    @Autowired
    private LoginController loginController;

    @Autowired
    private CadastroController cadastroController;

    @Test
    public void loginValido() {
        // Arrange
        String email = "jonatan@jonatan.com";
        String senha = "abcd1234";

        CadastroRequest requestCadastro = CadastroRequest.builder()
                .email(email)
                .senha(senha)
                .build();
        cadastroController.cadastrar(requestCadastro);

        LoginRequest request = LoginRequest.builder()
                .email(email)
                .senha(senha)
                .build();

        // Act
        ResponseEntity<LoginResponse> response = loginController.login(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody().getToken()));
    }

    @Test
    public void loginInvalido() {
        // Arrange
        String email = "jonatan@jonatan.com";
        String senha = "abcd1234";

        LoginRequest request = LoginRequest.builder()
                .email(email)
                .senha(senha)
                .build();

        // Act
        try {
            ResponseEntity<LoginResponse> response = loginController.login(request);
            assertTrue(false);
        } catch (Exception e) {
            // Assert
            assertTrue(e instanceof BadCredentialsException);
        }
    }

}
