package com.seguranca.demonstracao.controller;

import com.seguranca.demonstracao.common.AbstractConfigurationTests;
import com.seguranca.demonstracao.dto.CadastroRequest;
import com.seguranca.demonstracao.dto.LoginResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CadastroControllerTest extends AbstractConfigurationTests {

    @Autowired
    CadastroController cadastroController;

    @Test
    public void cadastrarCliente() {
        // Arrange
        CadastroRequest request = CadastroRequest.builder()
                .email("jonatan@jonatan.com")
                .senha("abcd1234")
                .build();

        // Act
        ResponseEntity<LoginResponse> response = cadastroController.cadastrar(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody().getToken()));
    }


}
