package com.seguranca.demonstracao.service;

import com.seguranca.demonstracao.domain.Produto;
import com.seguranca.demonstracao.repository.ProdutoRepository;
import com.seguranca.demonstracao.service.impl.ProdutoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    public void cadastrar() {
        // Arrange
        Produto produto = Produto.builder().build();

        // Act
        produtoService.cadastrar(produto);

        // Assert
        Mockito.verify(produtoRepository).save(produto);
    }

    @Test
    public void listar() {
        // Act
        List<Produto> resultado = produtoService.listar();

        // Assert
        Mockito.verify(produtoRepository).findAll();
    }

    @Test
    public void buscarPorId() {
        // Act
        Produto produto = produtoService.buscarPorId(1L);

        // Assert
        Mockito.verify(produtoRepository).findById(1L);
    }
}
