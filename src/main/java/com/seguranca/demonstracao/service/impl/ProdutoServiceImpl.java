package com.seguranca.demonstracao.service.impl;

import com.seguranca.demonstracao.domain.Produto;
import com.seguranca.demonstracao.repository.ProdutoRepository;
import com.seguranca.demonstracao.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }
}
