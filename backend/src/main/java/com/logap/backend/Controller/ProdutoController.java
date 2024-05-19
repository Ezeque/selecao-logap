package com.logap.backend.Controller;

import com.logap.backend.Models.Produto;
import com.logap.backend.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/salvar")
    public Produto salvarProduto(@RequestBody Produto produto){
        return produtoService.salvaProduto(produto);
    }

    @GetMapping("/todos")
    public List<Produto> recuperaTodosProdutos(){
        return produtoService.recuperaTodosProdutos();
    }

    @GetMapping("/falta")
    public List<Produto> recuperaProdutosFalta(){
        return produtoService.recuperaProdutosFalta();
    }

    @GetMapping("/buscar")
    public Optional<Produto> recuperaProduto(@RequestParam(name = "id") Long id){
        return produtoService.recuperaProduto(id);
    }
}
