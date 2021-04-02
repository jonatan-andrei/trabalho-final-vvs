package com.seguranca.demonstracao.controller;

import com.seguranca.demonstracao.common.AbstractConfigurationTests;
import com.seguranca.demonstracao.domain.Produto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProdutoControllerTest extends AbstractConfigurationTests {

    @Autowired
    private ProdutoController produtoController;

    @Test
    public void cadastrarProduto() {
        // Arrange
        Produto produto = Produto.builder()
                .nome("Geladeira")
                .imagem("https://images.colombo.com.br/produtos/782285/782285_refrigerador_consul_CRM43NK_certo_100_z.jpg?ims=450x450")
                .preco(BigDecimal.valueOf(2299.00))
                .build();

        // Act
        ResponseEntity<Produto> response = produtoController.cadastrar(produto);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        ResponseEntity<Produto> produtoCadastrado = produtoController.buscarPorId(response.getBody().getId());
        assertTrue(nonNull(produtoCadastrado.getBody()));
    }

    @Test
    public void listarProdutos() {
        // Arrange
        Produto produto1 = Produto.builder().nome("Geladeira").imagem("imagem1").preco(BigDecimal.valueOf(2299.00)).build();
        Produto produto2 = Produto.builder().nome("Fogão").imagem("imagem2").preco(BigDecimal.valueOf(1199.00)).build();
        produtoController.cadastrar(produto1);
        produtoController.cadastrar(produto2);

        // Act
        ResponseEntity<List<Produto>> response = produtoController.listar();

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void buscarPorId() {
        // Arrange
        Produto produto = Produto.builder().nome("Geladeira").imagem("imagem1").preco(BigDecimal.valueOf(2299.00)).build();
        Produto produtoCadastrado = produtoController.cadastrar(produto).getBody();

        // Act
        ResponseEntity<Produto> response = produtoController.buscarPorId(produtoCadastrado.getId());

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertTrue(nonNull(response.getBody()));
    }
}
