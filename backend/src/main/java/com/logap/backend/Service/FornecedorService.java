package com.logap.backend.Service;

import com.logap.backend.Models.Fornecedor;
import com.logap.backend.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    @Autowired
    public FornecedorRepository fornecedorRepository;

    public Fornecedor criaFornecedor(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public void excluirFornecedor(Fornecedor fornecedor){
        fornecedorRepository.delete(fornecedor);
    }
}
