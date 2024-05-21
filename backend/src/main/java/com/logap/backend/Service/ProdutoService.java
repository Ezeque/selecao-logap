package com.logap.backend.Service;

import com.logap.backend.Models.Produto;
import com.logap.backend.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> recuperaTodosProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> recuperaProdutosFalta(){
        return produtoRepository.findAllByQuantidade(0);
    }

    public Produto salvaProduto(Produto produto){
        System.out.println(produto.getName());
        return produtoRepository.save(produto);
    }

    public Optional<Produto> recuperaProduto(Long id){
        return produtoRepository.findById(id);
    }

    public void excluirProduto(Long id) {
        Optional<Produto> produto = recuperaProduto(id);
        produto.ifPresent(value -> produtoRepository.delete(value));
    }
}
