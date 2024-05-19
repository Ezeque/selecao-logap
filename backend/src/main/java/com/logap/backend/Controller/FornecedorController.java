package com.logap.backend.Controller;

import com.logap.backend.Models.Fornecedor;
import com.logap.backend.Service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Fornecedor")
public class FornecedorController {
    @Autowired
    public FornecedorService fornecedorService;

    @PostMapping("/criar")
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor){
        return fornecedorService.criaFornecedor(fornecedor);
    }

    @PostMapping("/excluir")
    public void excluirFornecedor(@RequestBody Fornecedor fornecedor){
        fornecedorService.excluirFornecedor(fornecedor);
    }
}
