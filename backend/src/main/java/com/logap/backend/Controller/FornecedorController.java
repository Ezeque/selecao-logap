package com.logap.backend.Controller;

import com.logap.backend.Models.Fornecedor;
import com.logap.backend.Service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    public FornecedorService fornecedorService;

    @PostMapping("/criar")
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor){
        return fornecedorService.criaFornecedor(fornecedor);
    }

    @DeleteMapping("/excluir")
    public void excluirFornecedor(@RequestBody Fornecedor fornecedor){
        fornecedorService.excluirFornecedor(fornecedor);
    }

    @GetMapping("/todos")
    public List<Fornecedor> recuperaTodosFornecedores(){
        return fornecedorService.recuperaTodosFornecedores();
    }
}
