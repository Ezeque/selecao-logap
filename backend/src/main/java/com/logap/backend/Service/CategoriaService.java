package com.logap.backend.Service;

import com.logap.backend.Models.Categoria;
import com.logap.backend.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria criaCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void excluiCategoria(Categoria categoria){
        categoriaRepository.delete(categoria);
    }

}
